package com.demo.multilanguage.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;

import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.demo.multilanguage.BuildConfig;
import com.demo.multilanguage.LocaleManager.LocaleManager;
import com.demo.multilanguage.R;
import com.demo.multilanguage.adapter.NavigationDrawerAdapter;
import com.demo.multilanguage.utils.BaseActivity;
import com.demo.multilanguage.utils.Common;
import com.demo.multilanguage.utils.Constants;
import com.demo.multilanguage.utils.Glob;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends BaseActivity {

    DrawerLayout drawer_layout;
    ImageView ivDrawer;
    ListView drawerListView;

    LocationManager locationManager;
    PopupWindow popupWindow;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View listHeaderView;

    ImageView iv_header_images;
    LinearLayout layout_image_hearder;

    ImageView iv_setting;
    AlertDialog alertDialoglang;
    AlertDialog alertDialog;

    LinearLayout home_banner;
    LinearLayout lay_custome_add;
    LinearLayout lay_ad_mob;
    LinearLayout lay_fb;
    ImageView iv_cust_add;
    LinearLayout adContainer;
    View adContainerReletive;
    private com.facebook.ads.AdView adView;
    FrameLayout fragment_container;
    LinearLayout defolt_ads_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ivDrawer = (ImageView) findViewById(R.id.ivDrawer);
        drawerListView = (ListView) findViewById(R.id.drawerListView);

        iv_header_images = findViewById(R.id.iv_header_images);
        layout_image_hearder = findViewById(R.id.layout_image_hearder);
        iv_setting = findViewById(R.id.iv_setting);

        defolt_ads_layout = findViewById(R.id.defolt_ads_layout);
        home_banner = findViewById(R.id.home_banner);
        lay_custome_add = findViewById(R.id.lay_custome_add);
        lay_ad_mob = findViewById(R.id.lay_ad_mob);
        lay_ad_mob.setVisibility(View.GONE);
        lay_fb = findViewById(R.id.lay_fb);
        lay_fb.setVisibility(View.GONE);
        iv_cust_add = findViewById(R.id.iv_cust_add);
        adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainerReletive = findViewById(R.id.adMobView);
        fragment_container = findViewById(R.id.fragment_container);

        initDrawer();

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent I = new Intent(MainActivity.this, SettingActivty.class);
//                startActivity(I);

                initiatePopupWindow(v);


            }
        });


    }

    public void initDrawer() {
        LayoutInflater inflater = getLayoutInflater();
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);


        drawerListView.addHeaderView(listHeaderView);
        drawerListView.setAdapter(new NavigationDrawerAdapter(this));
        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDrawerItem(position);
            }
        });
    }

    private void selectDrawerItem(int position) {

        switch (position) {
            case 1:


                break;

            case 2:

                LaguageDialg();

                break;

            case 3:

                String lang = "Text";


                Intent share = new Intent("android.intent.action.SEND");
                share.setType("text/plain");
                share.putExtra("android.intent.extra.SUBJECT", "PANCHANG");
                share.putExtra("android.intent.extra.TEXT", "" + lang + "\n\n play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
                MainActivity.this.startActivity(Intent.createChooser(share, "Share via"));

                break;
            case 4:
                Intent intent11 = new Intent(android.content.Intent.ACTION_VIEW);
                intent11.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName()));
                startActivity(intent11);
                break;

        }


        drawerListView.setItemChecked(position, true);
        drawer_layout.closeDrawer(Gravity.LEFT);

//        drawerLayout.closeDrawer(drawerListView);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity.this, BackActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }


    private void initiatePopupWindow(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup_element));
            popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);


            popupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, 2, 150);

            /*LinearLayout tv_set = (LinearLayout) layout.findViewById(R.id.tv_set);*/
            LinearLayout tv_share = (LinearLayout) layout.findViewById(R.id.tv_share);
            LinearLayout tv_rate = (LinearLayout) layout.findViewById(R.id.tv_rate);
            LinearLayout tv_lat = (LinearLayout) layout.findViewById(R.id.tv_lat);
            /*tv_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClcikCountandAdd();
                    ClcikCountandAddBackActivity();


                    Intent I = new Intent(MainActivity.this, SettingActivty.class);
                    startActivity(I);
                    popupWindow.dismiss();
                }
            });*/

            tv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String lang = "Text";

                    Intent share = new Intent("android.intent.action.SEND");
                    share.setType("text/plain");
                    share.putExtra("android.intent.extra.SUBJECT", "PANCHANG");
                    share.putExtra("android.intent.extra.TEXT", "" + lang + "\n\n play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
                    MainActivity.this.startActivity(Intent.createChooser(share, "Share via"));
                    popupWindow.dismiss();

//                    Intent share = new Intent("android.intent.action.SEND");
//                    share.setType("text/plain");
//                    share.putExtra("android.intent.extra.SUBJECT", "PANCHANG");
//                    share.putExtra("android.intent.extra.TEXT", "હું તમને આ એપ્લિકેશન ઇન્સ્ટોલ કરવાની ભલામણ કરું છું\n\nઆ એપ્લિકેશનમા તમે \nપંચાંગ,\nચોઘડિયા,\nજન્મ પત્રિકા,\nલગ્ન ગુણ મિલન,\nરાહુકાલ,\nઅગત્યની તિથિઓ અને તહેવારોની નોટિફિકેશન દ્વરા માહિતી તેમજ અન્ય અગત્યની\n" +
//                            "માહિતીઓ મેળવી શક્શો.\n\n play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName());
//                    MainActivity.this.startActivity(Intent.createChooser(share, "Share via"));

                }
            });

            tv_rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName()));
                    startActivity(intent);
                    popupWindow.dismiss();


                }
            });

            tv_lat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LaguageDialg();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LaguageDialg() {
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        final View confirmDialog = li.inflate(R.layout.diaglog_lang, null);

        TextView tv_ahir_gujrati_lan = confirmDialog.findViewById(R.id.tv_ahir_gujrati_lan);
        TextView tv_ahir_hindi_lan = confirmDialog.findViewById(R.id.tv_ahir_hindi_lan);
        TextView tv_ahir_english_lan = confirmDialog.findViewById(R.id.tv_ahir_english_lan);

        Constants.languagecusttome = Common.getPreferenceString(MainActivity.this, "languagecusttome", "");

        if (Constants.languagecusttome.equals("en")) {
            tv_ahir_english_lan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow, 0, R.drawable.ic_iconfinder_check_circle_2561353, 0);

        } else if (Constants.languagecusttome.equals("hi")) {
            tv_ahir_hindi_lan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow, 0, R.drawable.ic_iconfinder_check_circle_2561353, 0);


        } else if (Constants.languagecusttome.equals("gu")) {
            tv_ahir_gujrati_lan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow, 0, R.drawable.ic_iconfinder_check_circle_2561353, 0);

        } else {
            tv_ahir_english_lan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow, 0, R.drawable.ic_iconfinder_check_circle_2561353, 0);

        }
        tv_ahir_gujrati_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setPreferenceString(MainActivity.this, "languagecusttome", "gu");
                String languageToLoad = "gu"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();
            }
        });

        tv_ahir_hindi_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setPreferenceString(MainActivity.this, "languagecusttome", "hi");
                String languageToLoad = "hi"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();


            }
        });

        tv_ahir_english_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setPreferenceString(MainActivity.this, "languagecusttome", "en");
                String languageToLoad = "en"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();


            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setView(confirmDialog);
        alertDialoglang = alert.create();
        alertDialoglang.show();
        alertDialoglang.setCanceledOnTouchOutside(true);
    }

    private boolean setNewLocale(String language, boolean restartProcess) {

        LocaleManager.setNewLocale(this, language);

        //Log.d("llllllllll", "setNewLocale: "+language);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        if (restartProcess) {
            System.exit(0);
        } else {
            Toast.makeText(this, "Activity restarted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void IntenetConnection() {
        LayoutInflater li = LayoutInflater.from(getActivity());
        final View confirmDialog = li.inflate(R.layout.internet_conn, null);

        TextView tv_ok = confirmDialog.findViewById(R.id.tv_ok);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setView(confirmDialog);

        alertDialog = alert.create();

        alertDialog.show();

        alertDialog.setCanceledOnTouchOutside(true);


    }

    public void GetAdIds() {

        final String vercode = String.valueOf(BuildConfig.VERSION_CODE);

        final OkHttpClient client = new OkHttpClient();
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("", "");


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //  RequestBody body = RequestBody.create(new URLs().MEDIA_TYPE, postdata.toString());
        final Request request = new Request.Builder()
                .url(Glob.ApiName + "Appname=" + Constants.App_Name + "&param1=" + vercode + "&param2=" + "" + "&param3=" + "" + "&DeviceId=" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + "&Language=" + "G"
                )
                .get()
                .build();
        Log.d("newresponceapiADS", Glob.ApiName + "Appname=" + Constants.App_Name + "&param1=" + vercode + "&param2=" + "" + "&param3=" + "" + "&DeviceId=" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + "&Language=" + "G");
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                // Toast.makeText(getApplicationContext(),"====EEEE",Toast.LENGTH_SHORT).show();
                Log.d("responceapiADS", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {


                final String responceapi = response.body().string();
                Log.d("responceapiADS", responceapi);


                if (response.isSuccessful()) {
                    try {

                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                final JSONObject json;
                                try {
                                    json = new JSONObject(responceapi);
                                    JSONArray jsonArray = json.getJSONArray("Displaylist");
                                    // JSONArray DisplayList = json.getJSONArray("DisplayList");
                                    Log.e("DisplayListassss", "" + jsonArray);

                                    String BottomName = "";
                                    String BottomArrayName = "";
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        Log.e("jsonObject", "" + jsonObject);
                                        BottomArrayName = jsonObject.optString("BottomArrayName").toString();
                                        BottomName = jsonObject.optString(BottomArrayName).toString();
                                        Log.e("BottomArrayName", "" + BottomArrayName);
                                        Log.e("BottomName", "" + BottomName);
                                        JSONObject objname = new JSONObject(BottomName);
                                        Log.e("objname", "" + objname);
                                        JSONObject jsonyyy = new JSONObject(BottomName);
                                        Log.e("jsonyyy", "" + jsonyyy);
                                        final String finalBottomArrayName = BottomArrayName;
                                        final String finalBottomName = BottomName;

                                        Log.d("UI thread", "I am the UI thread");
                                        Log.d("UI thread", "" + finalBottomArrayName);
                                        if (finalBottomArrayName.equals("Home Banner")) {
                                            if (finalBottomArrayName.equals("")) {


                                            } else {
                                                try {
                                                    final JSONObject HomeBanner = new JSONObject(finalBottomName);
                                                    Constants.Home_admob_id = HomeBanner.getString("Admob");
                                                    Constants.Home_fb_id = HomeBanner.getString("Facebook");
                                                    Constants.Home_types = HomeBanner.getString("Type");
                                                    Constants.Home_Active = HomeBanner.getString("Active");
                                                    Constants.Home_Click = HomeBanner.getString("Alternativesetting");
                                                    Constants.Home_Image = HomeBanner.getString("Image");
                                                    Constants.Home_Url = HomeBanner.getString("Url");
                                                    Constants.Home_Alternativesetting = HomeBanner.getString("Alternativesetting");

                                                    Common.setPreferenceString(getApplicationContext(), "adsHome_Image", Constants.Home_Image);
                                                    Common.setPreferenceString(getApplicationContext(), "adsHome_Url", Constants.Home_Url);
                                                    Common.setPreferenceString(getApplicationContext(), "adsHome_Active", Constants.Home_Active);

                                                    if (Constants.Home_Active.equals("true") || Constants.Home_Active.equals("True")) {


                                                    } else {

                                                        Log.d("sgdfgghasfydau", "run: " + Constants.Home_Active);

                                                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                        params.setMargins(0, 0, 0, 0);
                                                        fragment_container.setLayoutParams(params);
                                                        home_banner.setVisibility(View.GONE);
                                                    }

                                                    if (Constants.Home_Active.equals("true") || Constants.Home_Active.equals("True")) {
//
                                                        Log.d("dgfsde5svgyxecc3fd", "run: ");

                                                        if (Constants.Home_types.equals("Google Admob")) {
                                                            lay_fb.setVisibility(View.GONE);
                                                            //lay_custome_add.setVisibility(View.GONE);


                                                            if (Constants.Home_admob_id.equals("")) {


                                                            } else {
                                                                final AdView mAdView = new AdView(getActivity());

                                                                final AdSize adaptiveSize = getAdSize();

                                                                mAdView.setAdSize(adaptiveSize);
                                                                mAdView.setAdUnitId(Constants.Home_admob_id);
                                                                ((RelativeLayout) adContainerReletive).addView(mAdView);
                                                                AdRequest adRequest = new AdRequest.Builder()
                                                                        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                                                        //.addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                                                                        .build();

                                                                mAdView.setAdListener(new AdListener() {
                                                                    @Override
                                                                    public void onAdLoaded() {

                                                                        lay_ad_mob.setVisibility(View.VISIBLE);
                                                                        // Code to be executed when an ad finishes loading.

                                                                        // Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();


                                                                        int heightPixels = adaptiveSize.getHeightInPixels(MainActivity.this);


                                                                        final float scale = getResources().getDisplayMetrics().density;
                                                                        int pixels = (int) (heightPixels * scale + 0.5f);


                                                                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                        params.setMargins(0, 0, 0, heightPixels);
                                                                        fragment_container.setLayoutParams(params);

                                                                        LinearLayout.LayoutParams paramsyy = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightPixels);
                                                                        paramsyy.gravity = Gravity.BOTTOM;
                                                                        home_banner.setLayoutParams(paramsyy);
                                                                        home_banner.setVisibility(View.VISIBLE);


                                                                    }

                                                                    @Override
                                                                    public void onAdFailedToLoad(int errorCode) {
                                                                        // Code to be executed when an ad request fails.

                                                                        if (Constants.Home_Click.equals("1") || Constants.Home_Click.equals("2")) {

                                                                            //  Toast.makeText(getApplicationContext(),""+Constants.Home_Click,Toast.LENGTH_SHORT).show();

                                                                            lay_ad_mob.setVisibility(View.GONE);
                                                                            //lay_custome_add.setVisibility(View.GONE);


                                                                            adView = new com.facebook.ads.AdView(getActivity(), Constants.Home_fb_id, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                                                                            adContainer.addView(adView);

                                                                            adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                                                                                @Override
                                                                                public void onError(Ad ad, AdError adError) {

                                                                                    lay_custome_add.setVisibility(View.GONE);

                                                                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                                    params.setMargins(0, 0, 0, 0);
                                                                                    fragment_container.setLayoutParams(params);
                                                                                    home_banner.setVisibility(View.VISIBLE);
                                                                                }

                                                                                @Override
                                                                                public void onAdLoaded(Ad ad) {

                                                                                    lay_fb.setVisibility(View.VISIBLE);
                                                                                    final float scale = getResources().getDisplayMetrics().density;
                                                                                    int pixels = (int) (50 * scale + 0.5f);

                                                                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                                    params.setMargins(0, 0, 0, pixels);
                                                                                    fragment_container.setLayoutParams(params);
                                                                                    home_banner.setVisibility(View.VISIBLE);

                                                                                    LinearLayout.LayoutParams paramsyy = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, pixels);
                                                                                    paramsyy.gravity = Gravity.BOTTOM;
                                                                                    home_banner.setLayoutParams(paramsyy);
                                                                                    home_banner.setVisibility(View.VISIBLE);
                                                                                }

                                                                                @Override
                                                                                public void onAdClicked(Ad ad) {

                                                                                }

                                                                                @Override
                                                                                public void onLoggingImpression(Ad ad) {

                                                                                }
                                                                            }).build());

                                                                            //adView.loadAd();
                                                                        } else {
                                                                            lay_custome_add.setVisibility(View.GONE);
                                                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                            params.setMargins(0, 0, 0, 0);
                                                                            fragment_container.setLayoutParams(params);
                                                                            home_banner.setVisibility(View.VISIBLE);
                                                                        }


                                                                    }

                                                                    @Override
                                                                    public void onAdOpened() {
                                                                        // Code to be executed when an ad opens an overlay that
                                                                        // covers the screen.
                                                                    }

                                                                    @Override
                                                                    public void onAdClicked() {
                                                                        // Code to be executed when the user clicks on an ad.
                                                                    }

                                                                    @Override
                                                                    public void onAdLeftApplication() {
                                                                        // Code to be executed when the user has left the app.
                                                                    }

                                                                    @Override
                                                                    public void onAdClosed() {
                                                                        // Code to be executed when the user is about to return
                                                                        // to the app after tapping on an ad.
                                                                    }
                                                                });
                                                                mAdView.loadAd(adRequest);
                                                            }


                                                        } else if (Constants.Home_types.equals("Facebook")) {
                                                            lay_ad_mob.setVisibility(View.GONE);
                                                            //lay_custome_add.setVisibility(View.GONE);


                                                            if (Constants.Home_fb_id.equals("")) {

                                                            } else {

                                                                adView = new com.facebook.ads.AdView(getActivity(), Constants.Home_fb_id, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                                                                adContainer.addView(adView);

                                                                adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                                                                    @Override
                                                                    public void onError(Ad ad, AdError adError) {

                                                                        if (Constants.Home_Click.equals("1") || Constants.Home_Click.equals("2")) {

                                                                            // Toast.makeText(getApplicationContext(),""+Constants.Home_Click,Toast.LENGTH_SHORT).show();
                                                                            //Toast.makeText(MainActivity.this, "Error: " + adError.getErrorMessage(), Toast.LENGTH_LONG).show();

                                                                            lay_fb.setVisibility(View.GONE);
                                                                            //lay_custome_add.setVisibility(View.GONE);

                                                                            AdView mAdView = new AdView(getActivity());

                                                                            final AdSize adaptiveSize = getAdSize();

                                                                            mAdView.setAdSize(adaptiveSize);
                                                                            mAdView.setAdUnitId(Constants.Home_admob_id);
                                                                            ((RelativeLayout) adContainerReletive).addView(mAdView);
                                                                            AdRequest adRequest = new AdRequest.Builder().build();
                                                                            mAdView.setAdListener(new AdListener() {
                                                                                @Override
                                                                                public void onAdLoaded() {

                                                                                    lay_ad_mob.setVisibility(View.VISIBLE);
                                                                                    // Code to be executed when an ad finishes loading.

                                                                                    int heightPixels = adaptiveSize.getHeightInPixels(MainActivity.this);

                                                                                    // final float scale = getResources().getDisplayMetrics().density;
                                                                                    //int pixels = (int) (50 * scale + 0.5f);

                                                                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                                    params.setMargins(0, 0, 0, heightPixels);
                                                                                    fragment_container.setLayoutParams(params);
                                                                                    home_banner.setVisibility(View.VISIBLE);


                                                                                    LinearLayout.LayoutParams paramsyy = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, heightPixels);
                                                                                    paramsyy.gravity = Gravity.BOTTOM;
                                                                                    home_banner.setLayoutParams(paramsyy);
                                                                                    home_banner.setVisibility(View.VISIBLE);
                                                                                }

                                                                                @Override
                                                                                public void onAdFailedToLoad(int errorCode) {
                                                                                    // Code to be executed when an ad request fails.

                                                                                    //Toast.makeText(MainActivity.this, "onAdFailedToLoad", Toast.LENGTH_LONG).show();

                                                                                    lay_custome_add.setVisibility(View.GONE);
                                                                                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                                    params.setMargins(0, 0, 0, 0);
                                                                                    fragment_container.setLayoutParams(params);
                                                                                    home_banner.setVisibility(View.VISIBLE);


                                                                                }

                                                                                @Override
                                                                                public void onAdOpened() {
                                                                                    // Code to be executed when an ad opens an overlay that
                                                                                    // covers the screen.
                                                                                    //  Toast.makeText(MainActivity.this, "onAdOpened", Toast.LENGTH_LONG).show();

                                                                                }

                                                                                @Override
                                                                                public void onAdClicked() {
                                                                                    // Code to be executed when the user clicks on an ad.
                                                                                }

                                                                                @Override
                                                                                public void onAdLeftApplication() {
                                                                                    // Code to be executed when the user has left the app.
                                                                                    //Toast.makeText(MainActivity.this, "onAdLeftApplication", Toast.LENGTH_LONG).show();

                                                                                }

                                                                                @Override
                                                                                public void onAdClosed() {
                                                                                    // Code to be executed when the user is about to return
                                                                                    // to the app after tapping on an ad.

                                                                                    //Toast.makeText(MainActivity.this, "onAdClosed", Toast.LENGTH_LONG).show();

                                                                                }
                                                                            });
                                                                            mAdView.loadAd(adRequest);


                                                                        } else {
                                                                            lay_custome_add.setVisibility(View.GONE);
                                                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                            params.setMargins(0, 0, 0, 0);
                                                                            fragment_container.setLayoutParams(params);
                                                                            home_banner.setVisibility(View.VISIBLE);
                                                                        }

                                                                    }

                                                                    @Override
                                                                    public void onAdLoaded(Ad ad) {


                                                                        lay_fb.setVisibility(View.VISIBLE);

                                                                        final float scale = getResources().getDisplayMetrics().density;
                                                                        int pixels = (int) (50 * scale + 0.5f);

                                                                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                        params.setMargins(0, 0, 0, pixels);
                                                                        fragment_container.setLayoutParams(params);
                                                                        home_banner.setVisibility(View.VISIBLE);

                                                                        LinearLayout.LayoutParams paramsyy = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, pixels);
                                                                        paramsyy.gravity = Gravity.BOTTOM;
                                                                        home_banner.setLayoutParams(paramsyy);
                                                                        home_banner.setVisibility(View.VISIBLE);

                                                                    }

                                                                    @Override
                                                                    public void onAdClicked(Ad ad) {

                                                                    }

                                                                    @Override
                                                                    public void onLoggingImpression(Ad ad) {

                                                                    }
                                                                }).build());


                                                                //adView.loadAd();


                                                            }


                                                        } else if (Constants.Home_types.equals("Custom")) {
                                                            lay_ad_mob.setVisibility(View.GONE);
                                                            lay_fb.setVisibility(View.GONE);
                                                            //lay_custome_add.setVisibility(View.VISIBLE);

                                                            //final float scale = getResources().getDisplayMetrics().density;
                                                            // int pixels = (int) (50 * scale + 0.5f);


                                                            //Glide.with(getActivity()).load(Constants.Home_Image).into(iv_cust_add);

                                                            Glide.with(getActivity().getApplicationContext())
                                                                    .asBitmap()
                                                                    .load(Constants.Home_Image)
                                                                    .into(new SimpleTarget<Bitmap>() {
                                                                        @Override
                                                                        public void onResourceReady(Bitmap bitmap,
                                                                                                    Transition<? super Bitmap> transition) {
                                                                            int w = bitmap.getWidth();
                                                                            int h = bitmap.getHeight();
                                                                            iv_cust_add.setImageBitmap(bitmap);

                                                                            Log.e("huuuuuuuu", "" + h);
                                                                            Log.e("wwwww", "" + w);

                                                                            DisplayMetrics displayMetrics = new DisplayMetrics();
                                                                            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                                                            int height = displayMetrics.heightPixels;
                                                                            int width = displayMetrics.widthPixels;
                                                                            Log.e("width", "" + width);
                                                                            int mainu = h * width;
                                                                            int kkk = mainu / w;


                                                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) fragment_container.getLayoutParams();
                                                                            params.setMargins(0, 0, 0, kkk);
                                                                            fragment_container.setLayoutParams(params);
                                                                            home_banner.setVisibility(View.VISIBLE);
                                                                            defolt_ads_layout.setVisibility(View.GONE);
                                                                            lay_custome_add.setVisibility(View.VISIBLE);
                                                                        }
                                                                    });


                                                            iv_cust_add.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {

                                                                    if (Constants.Home_Url.equals("")) {

                                                                    } else {
                                                                        Intent i = new Intent(Intent.ACTION_VIEW);
                                                                        i.setData(Uri.parse(Constants.Home_Url));
                                                                        startActivity(i);

                                                                    }
                                                                }
                                                            });
                                                        } else {
                                                            home_banner.setVisibility(View.GONE);
                                                        }


                                                    } else {
                                                        home_banner.setVisibility(View.GONE);
                                                    }


                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                        }

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        android.view.Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

}