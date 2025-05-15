package dev.mk.myadmob.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dev.mk.myadmob.DetailActivity
import dev.mk.myadmob.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.goToDetail.setOnClickListener(View.OnClickListener {
            startActivity(DetailActivity.newIntent(requireContext()))
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Ad Lifecycle Management
    override fun onResume() {
        super.onResume()
        try {
            binding.adView.resume()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            binding.adView.pause()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            binding.adView.destroy()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}