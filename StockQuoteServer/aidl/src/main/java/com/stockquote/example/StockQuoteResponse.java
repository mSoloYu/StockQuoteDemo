package com.stockquote.example;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mSolo on 2015/3/26.
 */
public class StockQuoteResponse implements Parcelable {

    private final StockQuote mResult;

    public StockQuoteResponse(StockQuote result) {
        mResult = result;
    }

    public StockQuote getResult() {
        return mResult;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(mResult);
    }

    public static final Parcelable.Creator<StockQuoteResponse> CREATOR
            = new Parcelable.Creator<StockQuoteResponse>() {
        public StockQuoteResponse createFromParcel(Parcel in) {
            return new StockQuoteResponse((StockQuote)in.readSerializable());
        }
        public StockQuoteResponse[] newArray(int size) {
            return new StockQuoteResponse[size];
        }
    };

}
