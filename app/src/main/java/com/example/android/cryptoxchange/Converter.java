package com.example.android.cryptoxchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Mifere on 11/5/2017.
 */

public class Converter extends AppCompatActivity {
    private double bitcoin;
    private double ethereum;
    private static final String LOG_TAG = "ConverterActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_main);


        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("dashBundle");
        String symbol = b.getString("currencySymbol");

        bitcoin = b.getDouble("btcExchange");
        ethereum = b.getDouble("ethExchange");

        TextView mBaseSymbol = (TextView) findViewById(R.id.base_symbol);
        mBaseSymbol.setText(symbol);
    }


    public void convertAmount (View view){
        EditText mInputCurrency = (EditText) findViewById(R.id.retrieve_amount);
        double convertingAmount = ParseDouble(String.valueOf(mInputCurrency.getText()));

        double amountToBtc = convertingAmount / bitcoin;
        double amountToEth = convertingAmount / ethereum;

        TextView mBasetoBtc = (TextView) findViewById(R.id.base_to_btc);
        mBasetoBtc.setText("BTC " + Double.toString(amountToBtc) );

        TextView mBasetoEth = (TextView) findViewById(R.id.base_to_eth);
        mBasetoEth.setText("ETH " + Double.toString(amountToEth));
    }


    private  double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }
}

