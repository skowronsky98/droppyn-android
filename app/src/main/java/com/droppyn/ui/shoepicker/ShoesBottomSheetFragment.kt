package com.droppyn.ui.shoepicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.droppyn.databinding.FragmentShoesBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShoesBottomSheetFragment : BottomSheetDialogFragment() {


    private val shoesBottomSheetViewModel: ShoesBottomSheetViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, ShoesBottomSheetViewModel.Factory(activity.application)).get(ShoesBottomSheetViewModel::class.java)
    }
    private lateinit var binding: FragmentShoesBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentShoesBottomSheetBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.bottomSheetViewModel = shoesBottomSheetViewModel




        return binding.root
    }



}