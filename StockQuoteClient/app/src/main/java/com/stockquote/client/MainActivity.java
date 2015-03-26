package com.stockquote.client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.stockquote.example.IStockQuoteService;
import com.stockquote.example.StockQuote;
import com.stockquote.example.StockQuoteRequest;
import com.stockquote.example.StockQuoteResponse;

/**
 * Created by mSolo on 2015/3/26.
 */
public class MainActivity extends Activity
        implements ServiceConnection {

    private static final String TAG = "MainActivity";

    private TextView mNameTv;
    private TextView mQuoteTv;

    private IStockQuoteService mService;

    @Override
    protected void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        setContentView(R.layout.main);
        mNameTv = (TextView) findViewById(R.id.name);
        mQuoteTv = (TextView) findViewById(R.id.quote);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!super.bindService(new Intent(IStockQuoteService.class.getName()),
                this, BIND_AUTO_CREATE)) {
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.unbindService(this);
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = IStockQuoteService.Stub.asInterface(service);
        setText();
    }

    public void onServiceDisconnected(ComponentName name) {
        mService = null;
    }

    private void setText() {

        final StockQuoteRequest request = new StockQuoteRequest(null,
                new String[]{"002024"}, StockQuoteRequest.Type.STOCKQUOTE_MULTIPLE);
        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "正在获取...", true);

        new AsyncTask<Void, Void, StockQuote>() {
            @Override
            protected StockQuote doInBackground(Void... params) {
                try {
                    StockQuoteResponse response = mService.retrieveStockQuote(request);
                    dialog.dismiss();
                    return response.getResult();
                } catch (RemoteException e) {
                    Log.wtf(TAG, "Failed to communicate with the service", e);
                    dialog.dismiss();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(StockQuote result) {
                dialog.dismiss();
                if (result == null) {
                    Toast.makeText(MainActivity.this, "获取失败",
                            Toast.LENGTH_LONG).show();
                } else {
                    mNameTv.setText(result.getStockName());
                    mQuoteTv.setText(result.getStockPrice());
                }
            }
        }.execute();
    }

}
