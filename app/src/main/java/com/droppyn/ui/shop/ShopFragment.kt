package com.droppyn.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private val shopViewModel: ShopViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, ShopViewModel.Factory(activity.application)).get(ShopViewModel::class.java)
    }
    private lateinit var binding: FragmentShopBinding
    private val shareDataShopViewModel: ShareDataShopViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShopBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.shopViewModel = shopViewModel

        binding.shoeRecyclerView.adapter = ShopAdapter(ShoeListener { shoe ->
            shareDataShopViewModel.setItem(shoe)
            shopViewModel.navigateToOffers()
        })

        shopViewModel.navigateToOffers.observe(viewLifecycleOwner, { nav ->
            if(nav) {
                findNavController().navigate(R.id.action_navigation_shop_to_offersFragment)
                shopViewModel.onOffersNavigated()
            }
        })


        return binding.root
    }



}