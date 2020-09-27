package com.sport.fantasypredictionnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class MatchActivity extends AppCompatActivity {
    TextView maintitle,match,time,date,venue;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private AdView adView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        maintitle= findViewById(R.id.text);
        match= findViewById(R.id.match);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        venue = findViewById(R.id.venue);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        t10 = findViewById(R.id.t10);

        Bundle bundle=getIntent().getExtras();

        String main=bundle.getString("team");
        String ma=bundle.getString("match");
        String time1=bundle.getString("time");
        String date1=bundle.getString("date");
        String venue1=bundle.getString("venue");

        String tm1=bundle.getString("t1");
        String tm2=bundle.getString("t2");
        String tm3=bundle.getString("t3");
        String tm4=bundle.getString("t4");
        String tm5=bundle.getString("t5");
        String tm6=bundle.getString("t6");
        String tm7=bundle.getString("t7");
        String tm8=bundle.getString("t8");
        String tm9=bundle.getString("t9");
        String tm10=bundle.getString("t10");

        maintitle.setText(main);
        match.setText(ma);
        time.setText(time1);
        date.setText(date1);
        venue.setText(venue1);

        t1.setText(tm1);
        t2.setText(tm2);
        t3.setText(tm3);
        t4.setText(tm4);
        t5.setText(tm5);
        t6.setText(tm6);
        t7.setText(tm7);
        t8.setText(tm8);
        t9.setText(tm9);
        t10.setText(tm10);

        banner();
        //showInterstitial();
    }

    private void banner(){
        AudienceNetworkAds.initialize(this);
        adView= new AdView(this,"239189574067822_247020486618064", AdSize.BANNER_HEIGHT_50);
        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.banner_ads1);
        linearLayout.addView(adView);
        adView.loadAd();
    }

    private void showInterstitial() {
        AudienceNetworkAds.initialize(this);
        interstitialAd=new InterstitialAd(this, "239189574067822_249808803005899");
        final InterstitialAdListener listener= new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                interstitialAd.loadAd(interstitialAd.buildLoadAdConfig()
                        .withAdListener(listener).build());
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        if(adView != null){
            adView.destroy();
        }
        if( interstitialAd != null){
            interstitialAd.destroy();
        }
        super.onDestroy();
    }
}