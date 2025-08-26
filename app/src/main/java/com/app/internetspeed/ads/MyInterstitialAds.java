package com.app.internetspeed.ads;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.internetspeed.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MyInterstitialAds {

    private static InterstitialAd mInterstitialAd; // Singleton instance for interstitial ad
    private final Context context;

    public MyInterstitialAds(Context context) {
        this.context = context;
        initAds();
    }

    /**
     * Initializes the interstitial ad by loading it using an ad request.
     */
    private void initAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                context,
                context.getResources().getString(R.string.interstitial_ad_unit_id),
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        Log.i("MyInterstitialAds", "Ad successfully loaded.");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("MyInterstitialAds", "Ad failed to load: " + loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    /**
     * Returns the instance of the interstitial ad.
     *
     * @return The loaded InterstitialAd instance or null if not available.
     */
    public static InterstitialAd getInstance() {
        return mInterstitialAd;
    }

    /**
     * Sets the interstitial ad instance (useful for callbacks or clearing).
     *
     * @param interstitialAd The InterstitialAd instance to set.
     */
    public static void setInstance(InterstitialAd interstitialAd) {
        mInterstitialAd = interstitialAd;
    }

    /**
     * Shows the interstitial ad if it is loaded.
     *
     * @param context The context to use for showing the ad.
     * @param callback The callback to handle full-screen ad events.
     */
    public void showAd(Context context, FullScreenContentCallback callback) {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(callback);
            mInterstitialAd.show((android.app.Activity) context);
            // Clear the ad instance after showing it to reload a new ad
            setInstance(null);
        } else {
            Log.w("MyInterstitialAds", "Ad is not loaded yet.");
        }
    }

    /**
     * Reloads the interstitial ad after it has been shown or dismissed.
     */
    public void reloadAd() {
        initAds();
    }
}
