## Adaptive Banner အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ နှင့် ဆောင်ရန်ရှောင်ရန်များ

# Inline Adaptive Banner

```

class CustomAdapter(
    private var items: List<Any>,
    private val context: Context, 
    private val listener: ItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CONTENT = 0
        private const val TYPE_AD = 1
        private const val AD_INTERVAL = 5 // Show ad after every 5 items
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is AdItem -> TYPE_AD
            else -> TYPE_CONTENT
        }
    }

     inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val adContainer: FrameLayout = itemView.findViewById(R.id.ad_container)
        private var adView: AdView? = null

        fun bindAd() {
            adContainer.removeAllViews()
            adView?.destroy()

            // Container width ကိုသေချာစစ်ပါ
            val displayMetrics = context.resources.displayMetrics
            val adWidthPixels = if (adContainer.width > 0) {
                adContainer.width
            } else {
                displayMetrics.widthPixels - 32.dpToPx() // Padding အတွက်နေရာချန်ထား
            }

            adView = AdView(context).apply {
                adUnitId = context.getString(R.string.inline_banner_ads_radio)

                // Adaptive ad size ကိုသေချာစစ်ပါ
                val adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
                    context,
                    (adWidthPixels / displayMetrics.density).toInt()
                )
                setAdSize(adSize)

                // AdListener ထည့်ပါ
                adListener = object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e("AdError", "Ad failed: ${error.message}")
                        // ပြဿနာရှာဖွေရန် error code ကိုကြည့်ပါ
                    }
                    override fun onAdLoaded() {
                        Log.d("AdSuccess", "Ad loaded")
                        adContainer.visibility = View.VISIBLE
                    }
                }

                adContainer.addView(this)
                loadAd(AdRequest.Builder().build())
            }
        }
    }

    // Extension function for dp to px conversion
    fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    inner class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        fun bindData(tvradioData: TVRadio) {
            title.text = tvradioData.name
            Glide.with(context)
                .load(tvradioData.imageUrl)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(cover)
            itemLayout.setOnClickListener {
                listener.onItemClicked(tvradioData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_AD -> AdViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_ad_container, parent, false)
            )
            else -> RadioViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_radio, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdViewHolder -> holder.bindAd()
            is RadioViewHolder -> holder.bindData(items[position] as TVRadio)
        }
    }

    override fun getItemCount() = items.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is AdViewHolder) {
            holder.clearAd()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<TVRadio>) {
        items = createMixedList(data)
        notifyDataSetChanged()
    }

    private fun createMixedList(radioItems: List<TVRadio>): List<Any> {
        val mixedList = mutableListOf<Any>()
        
        radioItems.forEachIndexed { index, item ->
            mixedList.add(item)
            // Insert ad after every AD_INTERVAL items
            if ((index + 1) % AD_INTERVAL == 0 && index != radioItems.lastIndex) {
                mixedList.add(AdItem())
            }
        }
        
        return mixedList
    }

    data class AdItem(val id: String = "ad_item")

    interface ItemClickListener {
        fun onItemClicked(data: TVRadio)
    }
}
```
