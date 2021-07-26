package com.droppyn.ui.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentsOffersBinding
import com.droppyn.ui.shop.ShareDataShopViewModel

class OffersFragment : Fragment() {

    private val offersViewModel: OffersViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, OffersViewModel.Factory(activity.application)).get(OffersViewModel::class.java)
    }
    private lateinit var binding: FragmentsOffersBinding
    private val shareDataShopViewModel: ShareDataShopViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentsOffersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.offersViewModel = offersViewModel



        //TODO Pass offer to OfferDetailFragment
        binding.offersRecyclerView.adapter = OffersAdapter(OfferListener { offer ->
            shareDataShopViewModel.setItem(offer)
            offersViewModel.navigateToOfferDetail()
        })

        offersViewModel.navToOfferDitail.observe(viewLifecycleOwner, { nav ->
            if(nav) {
                findNavController().navigate(R.id.action_offersFragment_to_offerDetailFragment)
                offersViewModel.navigateToOfferDetailFinished()
            }
        })

        shareDataShopViewModel.itemShoe.observe(viewLifecycleOwner, { shoe ->
            offersViewModel.setFilter(shoe)
        })




        return binding.root
    }


}