package com.example.android.cryptoxchange;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TextView mEmptyStateTextView;

    public static final String LOG_TAG = MainActivity.class.getName();


    /** Adapter for the list of currency comaprison */
    private static DashAdapter mAdapter;

    /** URL for cryptocurrency price comparison data from cryptocompare site site */
    private static final String CRYPTOCOMPARE_REQUEST_URL =" https://min-api.cryptocompare.com/data/pricemulti?fsyms=ETH,BTC&tsyms=NGN,CAD,CNY,BND,EUR,USD,AUD,CHF,DKK,GHS,HKD,INR,JPY,KZT,NAD,NZD,OMR,RUB,SAR,SGD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(toolbar);

        ListView currencyListView = (ListView) findViewById(R.id.listView);

        mEmptyStateTextView = (TextView) findViewById(R.id.in_view);
        currencyListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new DashAdapter(this, new ArrayList<Dashboard>());
        currencyListView.setAdapter(mAdapter);



        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Dashboard dashboardItem = mAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), Converter.class);
                Bundle b = new Bundle();
                b.putDouble("btcExchange", dashboardItem.getBtcExchange());
                b.putDouble("ethExchange", dashboardItem.getEthExchange());
                b.putString("currencySymbol", dashboardItem.getBaseCurrency());
                intent.putExtra("dashBundle", b);
                startActivity(intent);
            }
});

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            // Start the AsyncTask to fetch the earthquake data
            CryptoCurrencyAsyncTask task = new CryptoCurrencyAsyncTask();
            task.execute(CRYPTOCOMPARE_REQUEST_URL);

        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
}


         @Override
              public boolean onCreateOptionsMenu(Menu menu) {
              getMenuInflater().inflate(R.menu.menu_main, menu);
              return true;
         }
         @Override
              public boolean onOptionsItemSelected(MenuItem item) {
              int id = item.getItemId();
              if (id == R.id.action_settings) {
              return true;
              }
             return super.onOptionsItemSelected(item);
         }

    private class CryptoCurrencyAsyncTask extends AsyncTask<String, Void, List<Dashboard>> {
        @Override
        protected List<Dashboard> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Dashboard> result = QueryUtility.fetchDashboardData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Dashboard> data) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Set empty state text to display "No _developers found)
            mEmptyStateTextView.setText(R.string.no_currency);

            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
