package com.wordseveryday.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class NetHelper {

    public static String getWebPage(String urlStr){
        try {
            HttpGet httpRequest = new HttpGet(urlStr);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpResponse httpResponse = defaultHttpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String webPageStr = EntityUtils.toString(httpResponse.getEntity());
                return webPageStr;
            }else {
                Log.i("abc", "a");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
