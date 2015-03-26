// IStockQuoteService.aidl
package com.stockquote.example;

// Declare any non-default types here with import statements
import com.stockquote.example.StockQuoteRequest;
import com.stockquote.example.StockQuoteResponse;

interface IStockQuoteService {

    StockQuoteResponse retrieveStockQuote(in StockQuoteRequest request);

}
