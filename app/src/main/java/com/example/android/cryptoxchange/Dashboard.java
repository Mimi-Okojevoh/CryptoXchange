package com.example.android.cryptoxchange;

/**
 * Created by Mifere on 11/5/2017.
 */

public class Dashboard {


    private double mBtcExchange;
    private double mEthExchange;
    private String mBaseCurrency;

    public Dashboard ( String baseCurrency, double ethExchange, double btcExchange) {
        mBtcExchange = btcExchange;
        mEthExchange = ethExchange;
        mBaseCurrency = baseCurrency;
    }

    public  double getBtcExchange(){return mBtcExchange;}
    public double getEthExchange(){return mEthExchange;}
    public String getBaseCurrency(){return mBaseCurrency;}

}
