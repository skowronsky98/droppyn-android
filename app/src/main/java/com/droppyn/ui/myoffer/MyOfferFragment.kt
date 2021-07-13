package com.droppyn.ui.myoffer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.droppyn.R
import com.droppyn.databinding.FragmentMyOfferBinding
import com.droppyn.ui.home.MyOfferListViewModel

class MyOfferFragment : Fragment() {

    companion object {
        fun newInstance() = MyOfferFragment()
    }

    private lateinit var binding: FragmentMyOfferBinding

    private lateinit var viewModel: MyOfferViewModel

    private val listViewModel: MyOfferListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(this).get(MyOfferViewModel::class.java)


        binding = FragmentMyOfferBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.myOfferViewModel = viewModel


        listViewModel.item.observe(viewLifecycleOwner, {offer ->
            viewModel.setMyOffer(offer)
        })


        return binding.root
    }

}