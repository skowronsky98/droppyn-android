package com.droppyn.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.droppyn.R
import com.droppyn.databinding.FragmentHomeBinding
import com.droppyn.network.DroppynApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeFragment : Fragment() {

//  private lateinit var homeViewModel: HomeViewModel

  private val homeViewModel: HomeViewModel by lazy {
    val activity = requireNotNull(this.activity) {
      "You can only access the viewModel after onViewCreated()"
    }
    ViewModelProvider(this,HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
  }

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.lifecycleOwner = this
    binding.homeViewModel = homeViewModel


//    homeViewModel.getBrands().observe(viewLifecycleOwner, { brands ->
//        brands.forEach { Log.i("room",it.name) }
//    })

      homeViewModel.getShoes().observe(viewLifecycleOwner, { shoes ->
        shoes.forEach { Log.i("room",it.model+" "+ it.brand.name) }
    })


//      homeViewModel.getShoesAndBrand().observe(viewLifecycleOwner, { shoes ->
//        shoes.forEach { Log.i("room",it.shoe.model + " ") }
//    })


    return binding.root
  }


}