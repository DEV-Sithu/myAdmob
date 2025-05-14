package dev.mk.myadmob

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.appopen.AppOpenAd


class MyApp : Application(), Application.ActivityLifecycleCallbacks {

    private var currentActivity: Activity? = null
    private var appOpenAdManager: AppOpenAdManager? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        registerActivityLifecycleCallbacks(this)

        appOpenAdManager = AppOpenAdManager()
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
        appOpenAdManager?.showAdIfAvailable(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    // Other lifecycle methods...
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    inner class AppOpenAdManager {
        private var appOpenAd: AppOpenAd? = null
        private var isLoadingAd = false
        private var isShowingAd = false
        private var loadTime: Long = 0

        fun showAdIfAvailable(activity: Activity) {
            if (isShowingAd) return
            if (isAdAvailable()) {
                appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                    }

                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null
                        isShowingAd = false
                        loadAd(activity)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        isShowingAd = false
                    }
                }
                appOpenAd?.show(activity)
            } else {
                loadAd(activity)
            }
        }

        private fun loadAd(context: Context) {
            if (isLoadingAd || isAdAvailable()) return

            isLoadingAd = true
            val request = AdRequest.Builder().build()
            AppOpenAd.load(
                context,
                context.getString(R.string.open_ads), // Test Ad Unit ID
                request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                object : AppOpenAd.AppOpenAdLoadCallback() {
                    override fun onAdLoaded(ad: AppOpenAd) {
                        appOpenAd = ad
                        isLoadingAd = false
                        loadTime = System.currentTimeMillis()
                    }

                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        isLoadingAd = false
                    }
                }
            )
        }

        private fun isAdAvailable(): Boolean {
            return appOpenAd != null && (System.currentTimeMillis() - loadTime < 4 * 60 * 60 * 1000)
        }
    }
}
