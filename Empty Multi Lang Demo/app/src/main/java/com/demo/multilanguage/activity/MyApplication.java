package com.demo.multilanguage.activity;

/**
 * Created by ravi on 16/11/17.
 */

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        /*AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);*/

        // initialize the AdMob app
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");



    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


   /* public synchronized Tracker getGoogleAnalyticsTracker() {
      *//*  AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);*//*
    }
*/


//    public void trackScreenView(String screenName) {
//        Tracker t = getGoogleAnalyticsTracker();
//
//        // Set screen name.
//        t.setScreenName(screenName);
//
//        // Send a screen view.
//        t.send(new HitBuilders.ScreenViewBuilder().build());
//
//        GoogleAnalytics.getInstance(this).dispatchLocalHits();
//    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}