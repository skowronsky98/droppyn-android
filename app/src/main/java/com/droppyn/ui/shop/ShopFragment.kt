package com.droppyn.ui.shop

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droppyn.R
import com.droppyn.databinding.FragmentProfileBinding
import com.droppyn.databinding.FragmentShopBinding
import com.droppyn.ui.profile.ProfileViewModel

class ShopFragment : Fragment() {

    private lateinit var shopViewModel: ShopViewModel
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shopViewModel =
                ViewModelProvider(this).get(ShopViewModel::class.java)
        binding = FragmentShopBinding.inflate(inflater, container, false)
        binding.shopViewModel = shopViewModel

        return binding.root
    }



}