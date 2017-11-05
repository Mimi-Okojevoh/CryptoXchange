package com.example.android.cryptoxchange;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mifere on 11/5/2017.
 */

public class DashAdapter extends ArrayAdapter<Dashboard> {

    private static final String LOG_TAG = DashAdapter.class.getSimpleName();

    public DashAdapter(Activity context, ArrayList<Dashboard> dashboard) {
        super(context, 0, dashboard);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dashboard_main, parent, false);
        }

        Dashboard currentDashboard = getItem(position);

        TextView bitcoinEquivalent = (TextView) listItemView.findViewById(R.id.btc_exchange);
        bitcoinEquivalent.setText(Double.toString(currentDashboard.getBtcExchange()));

        TextView ethereumEquivalent = (TextView) listItemView.findViewById(R.id.eth_exchange);
        ethereumEquivalent.setText(Double.toString(currentDashboard.getEthExchange()));

        TextView baseCurrency = (TextView) listItemView.findViewById(R.id.base_currency);
        baseCurrency.setText(currentDashboard.getBaseCurrency());

        return  listItemView;
    }
}
