package dev.mk.myadmob

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dev.mk.myadmob.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var interstitialAd: InterstitialAd? = null

    companion object {
        fun newIntent(context: Context) = Intent(context, DetailActivity::class.java).
        apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        MobileAds.initialize(this) {
            loadInterstitialAd()
        }

        binding.showMeAds.setOnClickListener(View.OnClickListener {
            showAds()
        })

    }
    private fun showAds()
    {
        // Show ad (when appropriate)
        if (interstitialAd != null) {
            interstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }
    private fun loadInterstitialAd()
    {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,getString(R.string.interstitial_ads), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("TAG","ee"+ adError?.toString().toString())
                interstitialAd = null
            }

            override fun onAdLoaded(interstitial: InterstitialAd) {
                Log.d("TAG", "Ad was loaded.")
                interstitialAd = interstitial

            }
        })

    }

}