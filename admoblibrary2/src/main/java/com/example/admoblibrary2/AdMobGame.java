package com.example.admoblibrary2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class AdMobGame extends LinearLayout {
    private Button intersticialButton, videoButton;
    private AdView mAdView;
    private InterstitialAd interstitialAd;
    private RewardedVideoAd rewardedVideoAd;
    private TextView coinsTxt;
    private int coinsQuantity;

    public AdMobGame(Context context) {
        super(context);
        initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
    }

    public AdMobGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
    }

    private void initialize(final Context context, OnInitializationCompleteListener onInitializationCompleteListener) {

        View view = inflate(context, R.layout.admobgame, this);

        intersticialButton = view.findViewById(R.id.intersticialButton);
        videoButton = view.findViewById(R.id.videoButton3);
        mAdView = view.findViewById(R.id.adViewBanner);
        coinsTxt = view.findViewById(R.id.textViewCoins);
        coinsQuantity = 0;

        MobileAds.initialize(context, onInitializationCompleteListener);

        //Request for each add
        final AdRequest adRequest = new AdRequest.Builder().build();
        //Loading the BANNER
        mAdView.loadAd(adRequest);
        if (mAdView.isLoading()){
            Toast.makeText(context, "Se está cargando el Banner!", Toast.LENGTH_LONG).show();
        }

        //Function defined down.
        initializeIntersticialAd(adRequest, context);

        //creating the rewardingAd
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {
                addCoins(1);
            }

            @Override
            public void onRewardedVideoAdClosed() {
                loadRewardVideoAd(adRequest);
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                addCoins(rewardItem.getAmount());
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });
        //Function to load the RewardVideoAd
        loadRewardVideoAd(adRequest);

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                    Toast.makeText(context, "Se ha cargado el Video bonificado!", Toast.LENGTH_LONG).show();
                }else {
                    loadRewardVideoAd(adRequest);
                    Toast.makeText(context, "Estamos cargando el Anuncion :D (Prueba de nuevo)", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Launching the new interstitialAd when the interstitialButton is pressed and the ad is loaded
        intersticialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                    Toast.makeText(context, "Se ha cargado el intersticial video!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context, "Estamos cargando el Anuncion :D (Prueba de nuevo)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Funtion to initialize the IntersticialAd
    private void initializeIntersticialAd(final AdRequest adRequestT, Context context){
        //loading interstitial Ad
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/8691691433");
        interstitialAd.loadAd(adRequestT);
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                addCoins(5);
                interstitialAd.loadAd(adRequestT);
            }
        });
    }
    //Function to load the Rewarde Video Ad
    private void loadRewardVideoAd(AdRequest adRequestV){
        if (!rewardedVideoAd.isLoaded()){
            rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", adRequestV);
        }
    }

    //Function for add coins to the player ;)
    private void addCoins(int coins) {
        coinsQuantity = coinsQuantity+ coins;
        coinsTxt.setText(" " + coinsQuantity);
    }

    private TextView getCoinsTxt() {
        return coinsTxt;
    }
}
