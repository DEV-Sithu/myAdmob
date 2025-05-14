# Google Admob ဆိုင်ရာဗဟုသုတများ

### Android Test Ad Unit IDs
#### androidသမာတွေအတွက် Test လုပ်ရင် အသုံးပြုရမဲ့ ads unitidတွေပါ

* App Open	
> ca-app-pub-3940256099942544/9257395921

* Adaptive Banner	
> ca-app-pub-3940256099942544/9214589741

* Fixed Banner
> ca-app-pub-3940256099942544/6300978111

* Interstitial
> ca-app-pub-3940256099942544/1033173712

* Rewarded	
> ca-app-pub-3940256099942544/5224354917

* Rewarded Interstitial	
> ca-app-pub-3940256099942544/5354046379

* Native	
> ca-app-pub-3940256099942544/2247696110

___

### iOS Test Ad Unit IDs
#### IOS သမာတွေအတွက် Test လုပ်ရင် အသုံးပြုရမဲ့ ads unitidတွေပါ

* App Open
> ca-app-pub-3940256099942544/5575463023

* Banner
> ca-app-pub-3940256099942544/2934735716

* Interstitial	
> ca-app-pub-3940256099942544/4411468910

* Rewarded	
> ca-app-pub-3940256099942544/1712485313

* Rewarded Interstitial	
> ca-app-pub-3940256099942544/6978759866

* Native
> ca-app-pub-3940256099942544/3986624511

___

## build.gradle မှာ ads lib လိုအပ်တာလေးတွေ  implement လုပ်မယ်

```
    //  ads
    implementation("com.google.android.gms:play-services-ads:22.6.0")
    // open ads lifecycle
    implementation("androidx.lifecycle:lifecycle-process:2.9.0")
```
___

## Android Manifest ဖိုင်ထဲမှာ permission ရယ် meta data ရယ်ထည့်မယ်

```
    <!-- For apps targeting Android 13 or higher & GMA SDK version 20.3.0 or lower -->
    <uses-permission android:name="com.google.android.gms.permission.AD_ID"/>
    <application android:name=".MyApp">
     <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="@string/admobId" />
    </application>

```
___

## App Open Ads အတွက် လုပ်ဆောင်ရဲ့အချက် Code snippets များ      [ ဒီမှာဖတ်ပါ ](AppOpenAds.md "AppOpenAds အကြောင်း")

 

