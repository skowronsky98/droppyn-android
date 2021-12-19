package com.droppyn.ui.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentsOffersBinding
import com.droppyn.ui.offers.filter.ShareFilterDataViewModel
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
    private val shareFilterDataViewModel: ShareFilterDataViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentsOffersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.offersViewModel = offersViewModel


        val adapter = OffersAdapter(OfferListener { offer ->
            shareDataShopViewModel.setItem(offer)
            offersViewModel.navigateToOfferDetail()
        })

        binding.offersRecyclerView.adapter = adapter



        offersViewModel.navToOfferDetail.observe(viewLifecycleOwner, { nav ->
            if(nav) {
                findNavController().navigate(R.id.action_offersFragment_to_offerDetailFragment)
                offersViewModel.navigateToOfferDetailFinished()
            }
        })

        shareDataShopViewModel.itemShoe.observe(viewLifecycleOwner, { shoe ->
            offersViewModel.setFilter(shoe)

        })

        shareFilterDataViewModel.item.observe(viewLifecycleOwner, { filter ->
            Log.i("filter", "Filter size: " + filter?.us?.toString())
            filter?.let {
                offersViewModel.setFilter(it)

            }

            //Attach new data because livedata was reassigned
            offersViewModel.offers.observe(viewLifecycleOwner, { offers ->
                adapter.submitList(offers)
            })


        })



        offersViewModel.navBackToShop.observe(viewLifecycleOwner, { nav ->
            if(nav){
                findNavController().popBackStack()
                offersViewModel.navBackToShopFinished()
            }
        })

        offersViewModel.navToFilter.observe(viewLifecycleOwner, { nav ->
            if(nav){
                findNavController().navigate(R.id.action_offersFragment_to_filterFragment)
                offersViewModel.navigateToFilterFinished()
            }
        })


        return binding.root
    }


}