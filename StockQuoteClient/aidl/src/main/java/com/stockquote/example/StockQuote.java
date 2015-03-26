package com.stockquote.example;

import java.io.Serializable;

/**
 * Created by mSolo on 2015/3/26.
 */
public class StockQuote implements Serializable {

    public static final int DOWN_COLOR = -1;
    public static final int EVEN_COLOR = 0;
    public static final int RISE_COLOR = 1;
    public static final int SUSPEND_COLOR = 13;

    private int priceColor = EVEN_COLOR;
    private String stockName;
    private String stockPrice;
    private String stockHighestPrice;
    private String stockLowestPrice;
    private String stockPriceGap;
    private String stockPercentage;

    private StockQuote() {
    }

    public StockQuote(int priceColor, String stockName, String stockPrice,
                      String stockHighestPrice, String stockLowestPrice,
                      String stockPriceGap, String stockPercentage) {
        this.priceColor = priceColor;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.stockHighestPrice = stockHighestPrice;
        this.stockLowestPrice = stockLowestPrice;
        this.stockPriceGap = stockPriceGap;
        this.stockPercentage = stockPercentage;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockHighestPrice() {
        return stockHighestPrice;
    }

    public void setStockHighestPrice(String stockHighestPrice) {
        this.stockHighestPrice = stockHighestPrice;
    }

    public String getStockLowestPrice() {
        return stockLowestPrice;
    }

    public void setStockLowestPrice(String stockLowestPrice) {
        this.stockLowestPrice = stockLowestPrice;
    }

    public String getStockPriceGap() {
        return stockPriceGap;
    }

    public void setStockPriceGap(String stockPriceGap) {
        this.stockPriceGap = stockPriceGap;
    }

    public String getStockPercentage() {
        return stockPercentage;
    }

    public void setStockPercentage(String stockPercentage) {
        this.stockPercentage = stockPercentage;
    }

    public int getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(int priceColor) {
        this.priceColor = priceColor;
    }

}
