package com.droppyn.ui.addoffer.item

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.droppyn.R
import com.droppyn.databinding.FragmentShoeItemBinding
import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe
import com.droppyn.ui.home.ShareDataMyOffersViewModel
import com.droppyn.ui.shoepicker.ShareDataShoeViewModel

class ShoeItemFragment : Fragment() {

    private lateinit var binding: FragmentShoeItemBinding

    private val shareDataShoeViewModel: ShareDataShoeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentShoeItemBinding.inflate(inflater, container, false)



        shareDataShoeViewModel.item.observe(viewLifecycleOwner, { shoe ->

//            Log.i("shoe",offer.id)

            Glide.with(requireContext())
                .load(shoe.media.imageUrl)
                .into(binding.shoeImage)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}