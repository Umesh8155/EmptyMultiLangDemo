package com.demo.multilanguage.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.ads.InterstitialAd;
import com.demo.multilanguage.LocaleManager.LocaleManager;
import com.demo.multilanguage.R;


/**
 * Created by root on 21/4/16.
 */
public class BaseActivity extends AppCompatActivity {

    private static Context mContext;

    private Toast toast;
    public Toolbar toolbar;
    TextView tv_title, tv_date_only;
    ImageView ivDrawer, iv_calendar, iv_location, iv_setting;
    PopupWindow popupWindow;

    InterstitialAd mInterstitialAd;

    private com.facebook.ads.InterstitialAd interstitialAd;

    /*public static String getLanguageName() {

        String lag=mContext.getResources().getString(R.string.l);

        return lag;
    }*/


    public void bindToolbar() {
        ivDrawer = (ImageView) findViewById(R.id.ivDrawer);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_date_only = (TextView) findViewById(R.id.tv_date_only);
        iv_setting = (ImageView) findViewById(R.id.iv_setting);

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupWindow(v);

            }
        });

    }

    public void setTitle(String titlea) {
        tv_title.setText(titlea + "");
    }

    public void showBack() {
        ivDrawer.setImageResource(R.drawable.ic_if_arrow_back_1063891);
        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void showEmptyOnly() {
        iv_calendar.setVisibility(View.GONE);
        iv_location.setVisibility(View.GONE);
        iv_setting.setVisibility(View.GONE);

    }

    public void showMenuOnly() {
        iv_calendar.setImageResource(R.drawable.ic_demoo);


    }

    public void Shareapp() {

        Intent share = new Intent("android.intent.action.SEND");
        share.setType("text/plain");
        share.putExtra("android.intent.extra.SUBJECT", "Teacher Application");
        share.putExtra("android.intent.extra.TEXT", "Download App Now & Get help you...\n\nDownload From :\nplay.google.com/store/apps/details?id=" + BaseActivity.this.getPackageName());
        BaseActivity.this.startActivity(Intent.createChooser(share, "Share via"));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        toast = Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
        mContext = this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    public void showToast(final String text, final int duration) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(text);
                toast.setDuration(duration);
                toast.show();
            }
        });
    }

    public void showToast(final int text, final int duration) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                toast.setText(getResources().getString(text));
                toast.setDuration(duration);
                toast.show();
            }
        });

    }

    public BaseActivity getActivity() {
        return this;
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    private void initiatePopupWindow(View v) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) BaseActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup_element));
            popupWindow = new PopupWindow(layout, 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);


            popupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, 2, 150);

            TextView tv_share = layout.findViewById(R.id.tv_share);
            TextView tv_rate = layout.findViewById(R.id.tv_rate);


            tv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent share = new Intent("android.intent.action.SEND");
                    share.setType("text/plain");
                    share.putExtra("android.intent.extra.SUBJECT", "PANCHANG");
                    share.putExtra("android.intent.extra.TEXT", "Welcome to Choghadiya.\nGujarat's No. 1 Educational Application\n\nThis application is specially design for Gujarat Students \nLastest Upates,\nGPSC,\nUPSC,\nGSSSB,\nGovenment Yojana,\nCourse Guidance,\nNews Papers,\nUsefull Websites,\nRojagar Samachar,\nCurrent Affaire,\nLatest education materials,\nNotifications provide, timely information about Choghadiya app.Gujarat's No. 1 Educational Application\n" +
                            "Click Download here.\n\n play.google.com/store/apps/details?id=" + BaseActivity.this.getPackageName());
                    BaseActivity.this.startActivity(Intent.createChooser(share, "Share via"));
                    popupWindow.dismiss();

                }
            });

            tv_rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + BaseActivity.this.getPackageName()));
                    startActivity(intent);
                    popupWindow.dismiss();

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
