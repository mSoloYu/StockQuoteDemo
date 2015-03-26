package com.stockquote.example;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mSolo on 2015/3/26.
 */
public class StockQuoteRequest implements Parcelable {

    public static enum Type {
        STOCKQUOTE_ONE, STOCKQUOTE_MULTIPLE
    }

    private final String mStockId;
    private final String[] mStockIdArray;

    private final Type mType;

    public StockQuoteRequest(String stockId, String[] stockIdArray, Type type) {
        this.mStockId = stockId;
        this.mStockIdArray = stockIdArray;
        this.mType = type;
    }

    public String getStockId() {
        return mStockId;
    }

    public String[] getStockIdArray() {
        return mStockIdArray;
    }

    public Type getType() {
        return mType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mStockId);
        parcel.writeStringArray(mStockIdArray);
        parcel.writeInt(mType.ordinal());
    }

    public static final Parcelable.Creator<StockQuoteRequest> CREATOR = new Parcelable.Creator<StockQuoteRequest>() {
        public StockQuoteRequest createFromParcel(Parcel in) {
            String stockId = in.readString();
            String[] stockIdArray = in.createStringArray();
            Type type = Type.values()[in.readInt()];
            return new StockQuoteRequest(stockId, stockIdArray, type);
        }

        public StockQuoteRequest[] newArray(int size) {
            return new StockQuoteRequest[size];
        }
    };

}
