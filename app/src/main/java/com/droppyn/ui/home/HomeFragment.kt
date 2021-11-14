package com.droppyn.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentHomeBinding
import com.droppyn.domain.Offer
import com.droppyn.network.dto.toDatabaseShoe

class HomeFragment : Fragment() {


  private val homeViewModel: HomeViewModel by lazy {
    val activity = requireNotNull(this.activity) {
      "You can only access the viewModel after onViewCreated()"
    }
    ViewModelProvider(this,HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
  }

  private lateinit var binding: FragmentHomeBinding

  private val shareDataMyOffersViewModel: ShareDataMyOffersViewModel<Offer> by activityViewModels()


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    binding.homeViewModel = homeViewModel

    binding.myOffersRecyclerView.adapter = MyOffersAdapter(MyOfferListener { myOffer ->
        shareDataMyOffersViewModel.setItem(myOffer)
        homeViewModel.navigateToMyOffer()
      })


    homeViewModel.myOffers.observe(viewLifecycleOwner, { myOffers ->
      if (myOffers.isNullOrEmpty()){
//        homeViewModel.refreshData()
      }
    })



//    binding.myOffersRecyclerView.itemAnimator?.changeDuration = 0;

    homeViewModel.navigateToMyOffer.observe(viewLifecycleOwner, { navigate ->
      if(navigate){
        findNavController().navigate(R.id.action_navigation_home_to_myOfferFragment)
        homeViewModel.onMyOfferNavigated()
      }
    })

    homeViewModel.navigateToAddOffer.observe(viewLifecycleOwner, { nav ->
      if(nav) {
        findNavController().navigate(R.id.action_navigation_home_to_addOfferFragment2)
        homeViewModel.onAddOfferNavigated()
      }
    })



    binding.swipeToRefresh.setOnRefreshListener {
        homeViewModel.refreshData()
        binding.swipeToRefresh.isRefreshing = false
    }

    return binding.root
  }

}