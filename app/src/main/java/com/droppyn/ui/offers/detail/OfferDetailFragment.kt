package com.droppyn.ui.offers.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.droppyn.R
import com.droppyn.databinding.FragmentOfferDetailBinding
import com.droppyn.ui.shop.ShareDataShopViewModel

class OfferDetailFragment : Fragment() {

    private val offerDetailViewModel: OfferDetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, OfferDetailViewModel.Factory(activity.application)).get(OfferDetailViewModel::class.java)
    }
    private lateinit var binding: FragmentOfferDetailBinding
    private val shareDataShopViewModel: ShareDataShopViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentOfferDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.offerDetailViewModel = offerDetailViewModel


        shareDataShopViewModel.itemOffer.observe(viewLifecycleOwner, {
            Log.i("share",it.id)

            Glide.with(requireContext())
                .load(it.shoe.media.imageUrl)
                .into(binding.imageView)


            offerDetailViewModel.setOffer(it)
        })


        offerDetailViewModel.navBackToOffers.observe(viewLifecycleOwner,{ nav ->
            if(nav) {
                findNavController().popBackStack()
                offerDetailViewModel.navBackToOffersFinished()
            }
        })

        return binding.root
    }


}