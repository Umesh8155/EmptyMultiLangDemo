package com.demo.multilanguage.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.multilanguage.LocaleManager.LocaleManager;
import com.demo.multilanguage.R;
import com.demo.multilanguage.utils.BaseActivity;
import com.demo.multilanguage.utils.Common;
import com.demo.multilanguage.utils.Constants;

public class SplashScreenActivity extends BaseActivity {

    AlertDialog alertDialoglang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Common.getPreferenceString(SplashScreenActivity.this, "languagecusttome", "").equals("")) {

            LaguageDialg();

        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 500);
        }

    }

    public void LaguageDialg() {
        LayoutInflater li = LayoutInflater.from(SplashScreenActivity.this);
        final View confirmDialog = li.inflate(R.layout.diaglog_lang, null);

        TextView tv_ahir_gujrati_lan = confirmDialog.findViewById(R.id.tv_ahir_gujrati_lan);
        TextView tv_ahir_hindi_lan = confirmDialog.findViewById(R.id.tv_ahir_hindi_lan);
        TextView tv_ahir_english_lan = confirmDialog.findViewById(R.id.tv_ahir_english_lan);

        Constants.languagecusttome = Common.getPreferenceString(SplashScreenActivity.this, "languagecusttome", "");
        Common.setPreferenceString(SplashScreenActivity.this, "daily_panchng", "ON");


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
                Common.setPreferenceString(SplashScreenActivity.this, "languagecusttome", "gu");
                String languageToLoad = "gu"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();


            }
        });

        tv_ahir_hindi_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setPreferenceString(SplashScreenActivity.this, "languagecusttome", "hi");
                String languageToLoad = "hi"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();


            }
        });

        tv_ahir_english_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.setPreferenceString(SplashScreenActivity.this, "languagecusttome", "en");
                String languageToLoad = "en"; // your language
                setNewLocale(languageToLoad, true);
                alertDialoglang.dismiss();


            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(SplashScreenActivity.this);
        alert.setView(confirmDialog);
        alertDialoglang = alert.create();
        alertDialoglang.show();
        alertDialoglang.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    alertDialoglang.dismiss();

                    String title = getResources().getString(R.string.title);
                    String message = getResources().getString(R.string.message);
                    String yes = getResources().getString(R.string.yes);
                    String no = getResources().getString(R.string.no);

                    new AlertDialog.Builder(SplashScreenActivity.this)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }

                            })
                            .setNegativeButton(no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    LaguageDialg();
                                }

                            })
                            .show();


                }
                return true;
            }
        });
        alertDialoglang.setCanceledOnTouchOutside(false);
    }

    private boolean setNewLocale(String language, boolean restartProcess) {

        LocaleManager.setNewLocale(this, language);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        if (restartProcess) {
            System.exit(0);
        } else {
            Toast.makeText(this, "Activity restarted", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        String title = getResources().getString(R.string.title);
        String message = getResources().getString(R.string.message);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton(no, null)
                .show();

    }

}


