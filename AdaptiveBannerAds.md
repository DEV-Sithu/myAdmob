## Adaptive Banner အတွက် လုပ်ဆောင်ရမည့်အချက်များ Code snippets များ နှင့် ဆောင်ရန်ရှောင်ရန်များ

# Inline Adaptive Banner

```
package mm.myanmartvradio.com.ui.radio

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import mm.myanmartvradio.com.R
import mm.myanmartvradio.com.model.TVRadio

class RadioAdapter(
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
            
            adView = AdView(context).apply {
                adUnitId = context.getString(R.string.inline_banner_ads_radio)
                
                // Get adaptive ad size
                val adSize = AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(
                    context, 
                    adContainer.width
                )
                setAdSize(adSize)
                
                adContainer.addView(this)
                loadAd(AdRequest.Builder().build())
            }
        }

        fun clearAd() {
            adView?.destroy()
            adView = null
        }
    }

    inner class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val description: TextView = itemView.findViewById(R.id.tvMessage)
        private val cover: ImageView = itemView.findViewById(R.id.ivCover)
        private val itemLayout: LinearLayout = itemView.findViewById(R.id.itemlayout)
        private val flagImg: ImageView = itemView.findViewById(R.id.flagImg)

        fun bindData(tvradioData: TVRadio) {
            title.text = tvradioData.name
            description.text = tvradioData.description

            Glide.with(context)
                .load(tvradioData.imageUrl)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(cover)

            val flagResId = if (tvradioData.country == "my") {
                R.drawable.myflag
            } else {
                R.drawable.thaiflag
            }

            Glide.with(context)
                .load(flagResId)
                .placeholder(R.drawable.myflag)
                .into(flagImg)

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
