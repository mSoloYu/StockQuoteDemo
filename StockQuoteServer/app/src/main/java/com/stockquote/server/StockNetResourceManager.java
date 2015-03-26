package com.stockquote.server;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.stockquote.example.StockQuote;

import java.io.IOException;

/**
 * Created by mSolo on 2015/3/13.
 */
// StockNetResourceManager.java
public class StockNetResourceManager {

    private static final String URL_STOCK_REC = "http://hq.sinajs.cn/list=";
    private static final StockNetResourceManager INSTANCE = new StockNetResourceManager();

    private final OkHttpClient client = new OkHttpClient();

    private StockNetResourceManager() {
    }

    public static StockNetResourceManager getInstance() {
        return INSTANCE;
    }

    public StockQuote setUpStockQuotes(String[] stockIds) {

        StringBuilder stockRecUrl = new StringBuilder(URL_STOCK_REC);
        for (String stockId : stockIds) {
            makeRealStockId(stockId, stockRecUrl);
            stockRecUrl.append(",");
        }

        stockRecUrl.deleteCharAt(stockRecUrl.length() - 1);

        Request request = new Request.Builder().url(stockRecUrl.toString()).build();

        try {
            return cookStockQuote(client.newCall(request).execute().body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public StockQuote cookStockQuote(String rawRecs) {

        // var hq_str_sz002024="苏宁云商,11.26,11.22,11.33,11.45,11.17,11.33,11.34,131241390,1484755809.02,
        // 153559,11.33,358350,11.32,396400,11.31,654870,11.30,151700,11.29,420400,11.34,315415,11.35,170927,11.36,202600,11.37,111001,11.38,
        // 2015-03-13,15:05:53,00";
        // name,open,lastClose,current,highest,lowest,close,**,vol(手),money(元),
        // after split, the related index will be :

        String[] stockRecItemArray = rawRecs.split(",");

        String name = stockRecItemArray[0].split("\"")[1];

        // hanlde suspend day
        if (stockRecItemArray[1].startsWith("0.000")) {
            return new StockQuote(StockQuote.EVEN_COLOR, name, stockRecItemArray[3],
                    stockRecItemArray[3], stockRecItemArray[3], "+0.00", "+0.00%");
        }

        String lastPriceStr = stockRecItemArray[2];
        String curPriceStr = stockRecItemArray[3];

        int lastPrice = Integer.parseInt(lastPriceStr.replace(".", ""));
        int curPrice = Integer.parseInt(curPriceStr.replace(".", ""));

        int priceColor = StockQuote.EVEN_COLOR;
        if (curPrice > lastPrice) {
            priceColor = StockQuote.RISE_COLOR;
        } else if (curPrice < lastPrice) {
            priceColor = StockQuote.DOWN_COLOR;
        }

        float dividerForGap = 100.00f;
        float divider = 100.00f;
        if (name.startsWith("上证指数") || name.startsWith("深证成指")) {
            dividerForGap = 1000.00f;
            stockRecItemArray[3] = stockRecItemArray[3].substring(0, stockRecItemArray[3].length() - 1);
            stockRecItemArray[4] = stockRecItemArray[4].substring(0, stockRecItemArray[4].length() - 1);
            stockRecItemArray[5] = stockRecItemArray[5].substring(0, stockRecItemArray[5].length() - 1);
        }
        String priceGap = String.format("%+.2f", (curPrice - lastPrice)/dividerForGap);
        String percentage = String.format("%+.2f%%", (curPrice - lastPrice) * divider/lastPrice);

        return new StockQuote(priceColor, name, stockRecItemArray[3],
                stockRecItemArray[4], stockRecItemArray[5], priceGap, percentage);

    }

    private void makeRealStockId(String stockId, StringBuilder realStockIdBuilder) {

        if (stockId.startsWith("sh") || stockId.startsWith("sz")) {
            realStockIdBuilder.append(stockId);
            return ;
        }

        if (stockId.startsWith("600") || stockId.startsWith("601")) {
            realStockIdBuilder.append("sh").append(stockId);
        } else {
            realStockIdBuilder.append("sz").append(stockId);
        }

    }

}
