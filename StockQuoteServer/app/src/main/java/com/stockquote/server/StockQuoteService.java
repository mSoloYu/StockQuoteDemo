package com.stockquote.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by mSolo on 2015/3/26.
 */
// StockQuoteService
public class StockQuoteService extends Service {

    private static final String TAG = "StockQuoteService";

    private StockQuoteServiceImpl mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new StockQuoteServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        mService = null;
        super.onDestroy();
    }

}
