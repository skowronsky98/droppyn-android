package com.droppyn.ui.shoepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.droppyn.databinding.FragmentShoesBottomSheetBinding
import com.droppyn.ui.shop.ShoeAdapter
import com.droppyn.ui.shop.ShoeListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShoesBottomSheetFragment : BottomSheetDialogFragment() {


    private val shoesBottomSheetViewModel: ShoesBottomSheetViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, ShoesBottomSheetViewModel.Factory(activity.application)).get(ShoesBottomSheetViewModel::class.java)
    }
    private lateinit var binding: FragmentShoesBottomSheetBinding

    private val shareDataShoeViewModel: ShareDataShoeViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentShoesBottomSheetBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.bottomSheetViewModel = shoesBottomSheetViewModel


        binding.shoeRecyclerView.adapter = ShoeAdapter(ShoeListener { shoe ->
            shareDataShoeViewModel.setItem(shoe)
            this.dismiss()
        })

        return binding.root
    }



}