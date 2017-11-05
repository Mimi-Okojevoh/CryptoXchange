package com.example.android.cryptoxchange;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mifere on 11/5/2017.
 */

       public class QueryUtility {
              private static final String LOG_TAG = QueryUtility.class.getSimpleName();

              private QueryUtility() {}

       public static ArrayList<Dashboard> fetchDashboardData(String requestUrl) {
              URL url = createUrl(requestUrl);
              String jsonResponse = null;
                 try {
                 jsonResponse = makeHttpRequest(url);
                 } catch (IOException e) {
                 Log.e(LOG_TAG, "Error closing input stream", e);
                 }

        ArrayList<Dashboard> dashboard = extractFeatureFromJson(jsonResponse);
        return dashboard;
        }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error!!!: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error retrieving currency.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            }
            return jsonResponse;
       }


             private static String readFromStream(InputStream inputStream) throws IOException {
                    StringBuilder output = new StringBuilder();
                    if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                    }
                    }
                   return output.toString();
                   }


    private static ArrayList<Dashboard> extractFeatureFromJson(String dashboardJSON) {
        if (TextUtils.isEmpty(dashboardJSON)) {
            return null;
        }


        ArrayList<Dashboard> dashboards = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(dashboardJSON);
            JSONObject ethereumPrice = response.getJSONObject("ETH");
            JSONObject bitcoinPrice = response.getJSONObject("BTC");

            JSONArray symbols = ethereumPrice.names();


            ArrayList<String> currencySymbols = new ArrayList<>();
            for ( int i = 0; i < symbols.length(); i++){
                currencySymbols.add(symbols.getString(i));
            }


            double nairaToETH = ethereumPrice.getDouble("NGN");
            double nairaToBTC = bitcoinPrice.getDouble("NGN");
            String nairaSymbol = currencySymbols.get(0);
            dashboards.add(new Dashboard(nairaSymbol,nairaToETH,nairaToBTC));

            double canadaToETH = ethereumPrice.getDouble("CAD");
            double canadaToBTC = bitcoinPrice.getDouble("CAD");
            String canadaSymbol = currencySymbols.get(1);
            dashboards.add(new Dashboard(canadaSymbol,canadaToETH,canadaToBTC));

            double chinaToETH = ethereumPrice.getDouble("CNY");
            double chinaToBTC = bitcoinPrice.getDouble("CNY");
            String chinaSymbol = currencySymbols.get(2);
            dashboards.add(new Dashboard(chinaSymbol,chinaToETH,chinaToBTC));

            double bruneiToETH = ethereumPrice.getDouble("BND");
            double bruneiToBTC = bitcoinPrice.getDouble("BND");
            String bruneiSymbol = currencySymbols.get(3);
            dashboards.add(new Dashboard(bruneiSymbol,bruneiToETH,bruneiToBTC));

            double euroToETH = ethereumPrice.getDouble("EUR");
            double euroToBTC = bitcoinPrice.getDouble("EUR");
            String euroSymbol =currencySymbols.get(4);
            dashboards.add(new Dashboard(euroSymbol,euroToETH,euroToBTC));

            double dollarToETH = ethereumPrice.getDouble("USD");
            double dollarToBTC = bitcoinPrice.getDouble("USD");
            String dollarSymbol = currencySymbols.get(5);
            dashboards.add(new Dashboard(dollarSymbol,dollarToETH,dollarToBTC));

            double aussieToETH = ethereumPrice.getDouble("AUD");
            double aussieToBTC = bitcoinPrice.getDouble("AUD");
            String aussieSymbol = currencySymbols.get(6);
            dashboards.add(new Dashboard(aussieSymbol,aussieToETH,aussieToBTC));

            double swissToETH = ethereumPrice.getDouble("CHF");
            double swissToBTC = bitcoinPrice.getDouble("CHF");
            String swissSymbol = currencySymbols.get(7);
            dashboards.add(new Dashboard(swissSymbol,swissToETH,swissToBTC));

            double kroneToETH = ethereumPrice.getDouble("DKK");
            double kroneToBTC = bitcoinPrice.getDouble("DKK");
            String kroneSymbol = currencySymbols.get(8);
            dashboards.add(new Dashboard(kroneSymbol,kroneToETH,kroneToBTC));

            double cediToETH = ethereumPrice.getDouble("GHS");
            double cediToBTC = bitcoinPrice.getDouble("GHS");
            String cediSymbol = currencySymbols.get(9);
            dashboards.add(new Dashboard(cediSymbol,cediToETH,cediToBTC));

            double hongToETH = ethereumPrice.getDouble("HKD");
            double hongToBTC = bitcoinPrice.getDouble("HKD");
            String hongSymbol = currencySymbols.get(10);
            dashboards.add(new Dashboard(hongSymbol,hongToETH,hongToBTC));

            double rupeeToETH = ethereumPrice.getDouble("INR");
            double rupeeToBTC = bitcoinPrice.getDouble("INR");
            String rupeeSymbol = currencySymbols.get(11);
            dashboards.add(new Dashboard(rupeeSymbol,rupeeToETH,rupeeToBTC));

            double yenToETH = ethereumPrice.getDouble("JPY");
            double yenToBTC = bitcoinPrice.getDouble("JPY");
            String yenSymbol = currencySymbols.get(12);
            dashboards.add(new Dashboard(yenSymbol,yenToETH,yenToBTC));

            double tengeToETH = ethereumPrice.getDouble("KZT");
            double tengeToBTC = bitcoinPrice.getDouble("KZT");
            String tengeSymbol = currencySymbols.get(13);
            dashboards.add(new Dashboard(tengeSymbol,tengeToETH,tengeToBTC));

            double namibiaToETH = ethereumPrice.getDouble("NAD");
            double namibiaToBTC = bitcoinPrice.getDouble("NAD");
            String namibiaSymbol = currencySymbols.get(14);
            dashboards.add(new Dashboard(namibiaSymbol,namibiaToETH,namibiaToBTC));

            double omaniToETH = ethereumPrice.getDouble("OMR");
            double omaniToBTC = bitcoinPrice.getDouble("OMR");
            String omaniSymbol = currencySymbols.get(16);
            dashboards.add(new Dashboard(omaniSymbol,omaniToETH,omaniToBTC));

            double rubbleToETH = ethereumPrice.getDouble("RUB");
            double rubbleToBTC = bitcoinPrice.getDouble("RUB");
            String rubbleSymbol = currencySymbols.get(17);
            dashboards.add(new Dashboard(rubbleSymbol,rubbleToETH,rubbleToBTC));

            double saudiToETH = ethereumPrice.getDouble("SAR");
            double saudiToBTC = bitcoinPrice.getDouble("SAR");
            String saudiSymbol = currencySymbols.get(18);
            dashboards.add(new Dashboard(saudiSymbol,saudiToETH,saudiToBTC));

            double singaporeToETH = ethereumPrice.getDouble("SGD");
            double singaporeToBTC = bitcoinPrice.getDouble("SGD");
            String singaporeSymbol = currencySymbols.get(19);
            dashboards.add(new Dashboard(singaporeSymbol,singaporeToETH,singaporeToBTC));



        } catch (JSONException e) {
            Log.e("QueryUtils", "ERROR PARSING", e);
        }
        return dashboards;
    }
}
