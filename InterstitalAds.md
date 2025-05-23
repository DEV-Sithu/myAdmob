## Interstital Ads အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ နှင့် ဆောင်ရန်ရှောင်ရန်များ

Interstitial ads ဆိုတာက မိုဘိုင်းအက်ပ်တွေ ဒါမှမဟုတ် ဝက်ဘ်ဆိုက်တွေမှာ အသုံးပြုတဲ့ ကြော်ငြာတစ်မျိုးဖြစ်ပါတယ်။ သူတို့ကို စခရင်အပြည့်ဖော်ပြလေ့ရှိပြီး အက်ပ်ရဲ့အစိတ်အပိုင်းတစ်ခုကနေ တစ်ခုသို့ ကူးပြောင်းချိန် (ဥပမာ - ဂိမ်းအဆင့်ပြီးတဲ့အခါ၊ သတင်းဆောင်းပါးဖတ်ပြီးချိန်) မှာ ပေါ်လာတတ်ပါတယ်။ ဒီကြော်ငြာမျိုးကို အသုံးပြုရခြင်းရဲ့ အဓိကရည်ရွယ်ချက်က စျေးကွက်ရှာဖွေရေး သို့မဟုတ် ဝင်ငွေရရှိရေးအတွက် ထိရောက်မှုရှိလို့ပါ။

### **Interstitial Ads ရဲ့ အချက်အလက်များ**

1.  **ပုံစံ** : စခရင်အပြည့်ဖော်ပြသော ကြော်ငြာများ (ဗီဒီယို သို့မဟုတ် ပုံနှင့်စာသား)။
    
2.  **အသုံးပြုသည့်နေရာ** : မိုဘိုင်းအက်ပ်များ၊ ဝက်ဘ်ဆိုက်များ၊ ဂိမ်းများ (အထူးသဖြင့် အဆင့်ကူးပြောင်းချိန်များ)။
    
3.  **အားသာချက်များ** :
    
    -   ထင်ရှားသောကြောင့် အသုံးပြုသူအာရုံစိုက်မှုရစေသည်။
        
    -   ကြော်ငြာဝင်ငွေကို ထိရောက်စွာရရှိစေနိုင်သည်။
        
    -   သင့်တော်သောအချိန်တွင် ဖော်ပြပါက အသုံးပြုသူအတွေ အနှောင့်အယှက်နည်းသည်။
        
4.  **အားနည်းချက်များ** :
    
    -   အလွန်အကျွံသုံးပါက အသုံးပြုသူများ စိတ်တိုနိုင်သည်။
        
    -   အက်ပ်အသုံးပြုမှုအတွေ့အကြုံကို ထိခိုက်စေနိုင်သည်။
        
5.  **အသုံးပြုရန်အကြံ** :
    
    -   သဘာဝကူးပြောင်းချိန်များ (ဥပမာ - ဂိမ်းအနိုင်ရချိန်) တွင်သာ ဖော်ပြပါ။
        
    -   တစ်နေ့လျှင် ကြိမ်ရည်ကို ကန့်သတ်ပါ။
        
    -   Google စသည့်ပလက်ဖောင်းများ၏ လမ်းညွှန်ချက်များကို လိုက်နာပါ။
        

### **ဥပမာများ**

-   **ဂိမ်းအက်ပ်** : အဆင့်တစ်ခုပြီးတိုင်း 5 စက္ကန့်ကြော်ငြာ။
    
-   **သတင်းအက်ပ်** : ဆောင်းပါးဖတ်ပြီးချိန်တွင် ပေါ်လာသော ကြော်ငြာ။
    

Interstitial ads ကို မဟာဗျူဟာကျကျအသုံးပြုပါက အသုံးပြုသူအတွက်လည်း အဆင်ပြေ၊ ဖန်တီးသူအတွက်လည်း ဝင်ငွေကောင်းစေနိုင်ပါတယ်။

```
class MainActivity : AppCompatActivity() {
    private var interstitialAd: InterstitialAd? = null
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712" // Test ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // AdMob ကို Initialize လုပ်ခြင်း
        MobileAds.initialize(this) {}
        
        // Interstitial Ad ကို Load လုပ်ပါ
        loadInterstitialAd()
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    Log.d("AdMob", "Ad loaded successfully")
                    setupAdCallbacks() // Callback များကို ချိတ်ဆက်ပါ
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                    Log.e("AdMob", "Ad failed to load: ${error.message}")
                }
            }
        )
    }
}
```
### ** ကြော်ငြာပြသခြင်း**
```
fun showInterstitialAd() {
    if (interstitialAd != null) {
        interstitialAd?.show(this)
    } else {
        Log.d("AdMob", "Ad not loaded yet")
        loadInterstitialAd() // Ad မရှိပါက ပြန်လည် Load လုပ်ပါ
    }
}
```

သင့်တော်သော အချိန် (ဥပမာ - ဂိမ်းအဆင့်ပြီးချိန်) တွင် `show()` ကို ခေါ်ပါ။




