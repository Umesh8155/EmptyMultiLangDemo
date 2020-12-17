package com.demo.multilanguage.activity;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.demo.multilanguage.R;

public class BackActivity extends AppCompatActivity {

    TextView tv_title;
    TextView tv_sub_tile;
    TextView tv_yes;
    TextView tv_no;


    LinearLayout home_banner;
    LinearLayout lay_custome_add;
    LinearLayout lay_ad_mob;
    LinearLayout lay_fb;
    ImageView iv_cust_add;
    LinearLayout adContainer;
    View adContainerReletive;
    private com.facebook.ads.AdView adView;

    InterstitialAd mInterstitialAd;
    private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        tv_title=findViewById(R.id.tv_title);
        tv_sub_tile=findViewById(R.id.tv_sub_tile);
        tv_yes=findViewById(R.id.tv_yes);
        tv_no=findViewById(R.id.tv_no);


        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(I);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
