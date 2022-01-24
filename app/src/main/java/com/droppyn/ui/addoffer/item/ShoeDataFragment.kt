package com.droppyn.ui.addoffer.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.droppyn.R
import com.droppyn.databinding.FragmentShoeDataBinding
import com.droppyn.databinding.FragmentShoeItemBinding
import com.droppyn.ui.shoepicker.ShareDataShoeViewModel

class ShoeDataFragment : Fragment() {

    private lateinit var binding: FragmentShoeDataBinding

    private val shareDataShoeViewModel: ShareDataShoeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShoeDataBinding.inflate(inflater, container, false)

        shareDataShoeViewModel.item.observe(viewLifecycleOwner, { shoe ->
            binding.shoe = shoe
        })

        return binding.root
    }


}