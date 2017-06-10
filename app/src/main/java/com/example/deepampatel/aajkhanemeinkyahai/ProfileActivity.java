package com.example.deepampatel.aajkhanemeinkyahai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

public class profileactivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mess1;
    private TextView mess2;
    private Button updateButton;
    private TextView mess3;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private FirebaseAuth firebaseAuth;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);//chaltay?h athamb
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6582570502811965/4592809730");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        updateButton=(Button)findViewById(R.id.updateButton);

        mess1=(TextView)findViewById(R.id.mess1);
        mess2=(TextView)findViewById(R.id.mess2);
        mess3=(TextView)findViewById(R.id.mess3);

        mess1.setOnClickListener(this);
        mess2.setOnClickListener(this);
        mess3.setOnClickListener(this);
        updateButton.setOnClickListener(this);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                flag=true;
            }

        });
    }

    public void getmess1Menu(){
        if(mInterstitialAd.isLoaded()&&!flag){
            mInterstitialAd.show();
        }
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess1");
        startActivity(intent);

    }
    public void getmess2Menu(){
        if(mInterstitialAd.isLoaded()&&!flag){
            mInterstitialAd.show();
        }
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess2");
        startActivity(intent);
    }
    public void getmess3Menu(){
        if(mInterstitialAd.isLoaded()&&!flag){
            mInterstitialAd.show();
        }
        Intent intent = new Intent(profileactivity.this,seeMenu.class);
        intent.putExtra("MESS_NAME", "Mess3");
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        if(v==mess1)
        {
         getmess1Menu();
        }
        else if(v==mess2)
        {
            getmess2Menu();
        }
        else if(v==mess3)
        {
            getmess3Menu();
        }
        else if(v==updateButton)
        {

            startActivity(new Intent(profileactivity.this,updateMenu.class));
        }
    }
}
