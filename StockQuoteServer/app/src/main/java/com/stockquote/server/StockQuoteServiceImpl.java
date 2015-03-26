package com.stockquote.server;

import android.os.RemoteException;

import com.stockquote.example.IStockQuoteService;
import com.stockquote.example.StockQuote;
import com.stockquote.example.StockQuoteRequest;
import com.stockquote.example.StockQuoteResponse;

/**
 * Created by mSolo on 2015/3/26.
 */
public class StockQuoteServiceImpl extends IStockQuoteService.Stub {

    private static final String TAG = "StockQuoteServiceImpl";

    @Override
    public StockQuoteResponse retrieveStockQuote(StockQuoteRequest request)
            throws RemoteException {

        String[] stockIdArray = null;
        if (request.getType() == StockQuoteRequest.Type.STOCKQUOTE_MULTIPLE) {
            stockIdArray = request.getStockIdArray();
        } else {
            return null;
        }

        StockQuote stockQuote = StockNetResourceManager.getInstance()
                .setUpStockQuotes(stockIdArray);

        return new StockQuoteResponse(stockQuote);

    }

}
