## Fixed Banner Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ နှင့် ဆောင်ရန်ရှောင်ရန်များ

AdMob Fixed Banner Ads သည် အရွယ်အစားသတ်မှတ်ထားသော စတုဂံပုံစံ ကြော်ငြာများကို ဖော်ပြသည့် နည်းလမ်းဖြစ်ပါသည်။ ၎င်းတို့ကို အသုံးပြုရန် အောက်ပါအချက်များကို အသေးစိတ်ရှင်းပြမည်။

* * *

### 1\. **Fixed Banner Ads ၏ အဓိပ္ပာယ်နှင့် လက္ခဏာများ**

-   **သတ်မှတ်အရွယ်အစား** : Fixed Banner Ads သည် အမြဲတမ်း သတ်မှတ်ထားသော အလျား၊ အနံ (ဥပမာ 320x50 dp) ဖြင့် ဖော်ပြသည်။ Adaptive Banners ကဲ့သို့ စက်ပစ္စည်း၏ စခရင်အရွယ်အစားနှင့် လိုက်လျောညီထွေပြောင်းလဲမှု မရှိပါ 23။
    
-   **အသုံးပြုရာတွင် သတိပြုရမည့်အချက်** : ကြော်ငြာအတွက် သတ်မှတ်ထားသော Container အရွယ်အစားသည် ကြော်ငြာ၏ အရွယ်ထက် ငယ်နေပါက ကြော်ငြာမပေါ်နိုင်ပါ (ဥပမာ 320x50 dp ကြော်ငြာအတွက် Container သည် 288x495 dp သာရှိပါက မပြသပါ) 2။
    

* * *

### 2\. **အသုံးပြုနိုင်သော စံအရွယ်အစားများ**

AdMob တွင် အောက်ပါ Fixed Banner အရွယ်အစားများကို သတ်မှတ်ထားပါသည် 

| အရွယ်အစား (dp) | ဖော်ပြချက် | AdSize Constant |
| ------ | --- | ----- |
| 320x50    | Banner   | 	BANNER    |
| 320x100      | Large Banner   | LARGE_BANNER     |
| 300x250    | IAB Medium Rectangle  | MEDIUM_RECTANGLE     |
| 468x60     | IAB Full-Size Banner  | FULL_BANNER (တက်ဘလက်များအတွက်)     |
| 728x90    | IAB Leaderboard  | LEADERBOARD (တက်ဘလက်များအတွက်)   |

* * *

### 3\. **အသုံးပြုနည်း (Implementation)**

#### (က) XML Layout တွင် တိုက်ရိုက်ထည့်သွင်းခြင်း
```
<com.google.android.gms.ads.AdView
    xmlns:ads="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner_ad_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    ads:adSize="BANNER"
    ads:adUnitId="ca-app-pub-3940256099942544/6300978111" />
```

-   **အရေးကြီးသော Attributes** :
    
    -   `ads:adSize` : ကြော်ငြာ၏ အရွယ်အစား (ဥပမာ `BANNER`, `MEDIUM_RECTANGLE`)
        
    -   `ads:adUnitId` : AdMob မှ ရရှိသော Ad Unit ID 2။
        

#### (ခ) Programmatic နည်းဖြင့် ထည့်သွင်းခြင်း (Java/Kotlin)

```
val adView = AdView(context)
adView.adUnitId = "AD_UNIT_ID"
adView.setAdSize(AdSize.BANNER)
adContainer.addView(adView)
val adRequest = AdRequest.Builder().build()
adView.loadAd(adRequest)
```

* * *

### 4\. **အသုံးပြုသင့်သည့် အခြေအနေများ**

-   **အရွယ်အစား တိကျစွာ လိုအပ်သည့်အခါ** : ဥပမာ - ကြော်ငြာကို စာမျက်နှာ၏ အောက်ခြေ/ထိပ်တွင် သတ်မှတ်အရွယ်ဖြင့် ပြသလိုသည့်အခါ။
    
-   **Mediation နှင့် အသုံးပြုသည့်အခါ** : အချို့သော Third-Party Ad Networks များသည် Adaptive Banners ကို မပံ့ပိုးပါက Fixed Size ကို အသုံးပြုရန် လိုအပ်သည် 6။
    

* * *

### 5\. **သတိပြုရမည့်အချက်များ**

-   **Mediation ပြဿနာများ** : အချို့ Ad Networks များသည် သတ်မှတ်ထားသော အရွယ်ထက် ငယ်သော ကြော်ငြာများကို ပြသခြင်းဖြစ်နိုင်သည် (ဥပမာ - AppLovin သည် MREC (300x250) ကို မပံ့ပိုးပါ) 6။
    
-   **Test Ads အသုံးပြုခြင်း** : အကောင့်မပိတ်ခံရစေရန် `ca-app-pub-3940256099942544/6300978111` ကဲ့သို့သော Test Ad Unit ID ကို အသုံးပြုပါ 1။
    

* * *

### 6\. **Adaptive Banners နှင့် ကွာခြားချက်**

-   **Fixed Banners** : အရွယ်အစား မပြောင်းလဲပါ။ စခရင်အရွယ်နှင့် လိုက်လျောညီထွေ မရှိပါ။
    
-   **Adaptive Banners** : စက်ပစ္စည်း၏ စခရင်အရွယ်နှင့် လိုက်လျောညီထွေ အရွယ်အစားကို အလိုအလျောက် ချိန်ညှိပေးသည်


##### Fragment နဲ့ Banner Ad တွဲသုံးမယ်ဆိုရင် အောက်က ဥပမာအတိုင်း Lifecycle တွေကို သေချာ Handle လုပ်ပေးရပါမယ်။ 

1. Fragment Layout XML ထဲမှာ AdView ကို ထည့်ပါ (fragment_ad_example.xml)
 ```
   <?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:ads="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Your other views here -->

    <com.google.android.gms.ads.AdView
        android:id="@+id/adView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|center"
        ads:adSize="BANNER"
        ads:adUnitId="YOUR_BANNER_AD_UNIT_ID"/>

</FrameLayout>
```

2. Fragment Class ထဲမှာ AdView ကို Initialize လုပ်ပါ
```
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class ExampleFragment : Fragment() {

    private lateinit var adView: AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ad_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // AdView Initialize
        adView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    // Lifecycle ကို ဂရုစိုက်ပါ
    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onPause() {
        super.onPause()
        adView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
    }
}
```

**အရေးကြီးတဲ့အချက်များ:**

1.  `YOUR_BANNER_AD_UNIT_ID` နေရာမှာ သင့်ရဲ့ Ad Unit ID ကို အစားထိုးပါ
    
2.  AdView ရဲ့ Lifecycle methods (resume, pause, destroy) တွေကို Fragment Lifecycle နဲ့ Sync လုပ်ပေးရပါမယ်
    
3.  `AdRequest` ကို မမေ့ပါနဲ့၊ ဒါမှ Ad ကို Load လုပ်မှာ
    
4.  AdMob သုံးဖို့ Manifest နဲ့ Gradle မှာ Config လုပ်ဖို့မမေ့ပါနဲ့
    

**သတိထားရမယ့်အချက်:** Fragment ကို XML မှာ AdView မထည့်ထားဘဲ Programmatic ထည့်ချင်ရင် `addView()` နဲ့ ထည့်ပြီး LayoutParams တွေ သေချာသတ်မှတ်ပေးဖို့လိုပါတယ်။



    
