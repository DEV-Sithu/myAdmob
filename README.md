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
    <application android:name=".MyApp"   android:usesCleartextTraffic="true">
     <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="@string/admobId" />
    </application>

```
___

#### app ကို release ထုတ်တဲ့အခါ ဖိုင်sizeနည်းအောင်ရယ် reverse engeerning လုပ်ရင်codeတွေမတွေ့အောင်   isMinifyEnabled = true လုပ်ရင် proguard ruleမှာဒီလိုလေးတွေရေးရမယ်
```
 buildTypes {
        release {
            isMinifyEnabled = true  // code
            isShrinkResources  = true  // resource

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
```
##### proguard ruleမှာရေးရမှာတွေက
```
-keep public class com.google.android.gms.ads.** { public *; }
-keep public class com.google.ads.** { public *; }
```

___

#### Test Device ID တွေထည့်ခြင်း

1. Admob console မှာ TestDevice Id ထည့်ထားသင့်ပါတယ် (သင့်ဖုန်းကနေ တကယ့်ကြော်ငြာအစစ်တွေပြသမှုကို policyမထိအောင်ကာကွယ်ဖို့ပါ)
    * လုပ်နည်း(၁) ကို့ဖုန်းရဲံ setting ထဲကနေ Pravicy > Advance Pravicy > Ads > device ads id ကို ရပါပီ
    * လုပ်နည်း(၂) ကို့ဖုန်းရဲံ setting ထဲကနေ Google > Google Services > All services > Ads > device ads id ကိုရပါပြီ
   
___


##### Invalid Traffic ဆိုတာဘာလဲ အသေးစိတ်ဖတ်နိုင်ပါတယ်   [ ဒီမှာဖတ်ပါ ](InvalidTraffic.md "InvalidTraffic အကြောင်း")
___

##### App Open Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](AppOpenAds.md "AppOpenAds အကြောင်း")

##### Adaptive Banner Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](AdaptiveBannerAds.md "AdaptiveBannerAds အကြောင်း")

##### Fixed Banner Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](FixedBannerAds.md "FixedBannerAds အကြောင်း")

##### Interstital Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](InterstitalAds.md "InterstitalAds အကြောင်း")

##### Rewarded Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](RewardedAds.md "RewardAds အကြောင်း")

##### Rewarded Interstital Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](RewardedInterstitalAds.md "RewardedInterstitalAds အကြောင်း")

##### Native Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ   [ ဒီမှာဖတ်ပါ ](NativeAds.md "NativeAds အကြောင်း")
___

