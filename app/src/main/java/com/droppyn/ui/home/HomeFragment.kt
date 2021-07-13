package com.droppyn.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentHomeBinding
import com.droppyn.network.DroppynApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeFragment : Fragment() {


  private val homeViewModel: HomeViewModel by lazy {
    val activity = requireNotNull(this.activity) {
      "You can only access the viewModel after onViewCreated()"
    }
    ViewModelProvider(this,HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
  }

  private lateinit var binding: FragmentHomeBinding

  private val listViewModel: MyOfferListViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    binding.homeViewModel = homeViewModel

    binding.myOffersRecyclerView.adapter = MyOffersAdapter(MyOfferListener { myOffer ->
      homeViewModel.onMyOfferClicked(myOffer)
      listViewModel.setItem(myOffer)

    })

    homeViewModel.navigateToMyOffer.observe(viewLifecycleOwner, { myOffer ->
          myOffer?.let { this.findNavController().navigate(R.id.action_navigation_home_to_myOfferFragment)
          homeViewModel.onMyOfferNavigated()
      }
    })

    return binding.root
  }


}