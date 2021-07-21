package com.droppyn.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.droppyn.databinding.FragmentShopBinding
import com.droppyn.domain.Shoe
import com.droppyn.ui.home.ListViewModel

class ShopFragment : Fragment() {

    private val shopViewModel: ShopViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, ShopViewModel.Factory(activity.application)).get(ShopViewModel::class.java)
    }
    private lateinit var binding: FragmentShopBinding
    private val listViewModel: ListViewModel<Shoe> by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShopBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.shopViewModel = shopViewModel

        //TODO add listener
        binding.shoeRecyclerView.adapter = ShopAdapter(ShoeListener { shoe ->
            listViewModel.setItem(shoe)

        })



        return binding.root
    }



}