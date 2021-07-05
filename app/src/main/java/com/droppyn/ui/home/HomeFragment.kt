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

  private lateinit var homeViewModel: HomeViewModel
  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)


    binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.homeViewModel = homeViewModel

    homeViewModel.refreshData()

    return binding.root
  }


}