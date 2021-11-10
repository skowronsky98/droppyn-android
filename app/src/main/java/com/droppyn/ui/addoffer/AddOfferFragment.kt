package com.droppyn.ui.addoffer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentAddOfferBinding
import com.droppyn.domain.Shoe
import com.droppyn.ui.home.ShareDataMyOffersViewModel
import com.droppyn.ui.myoffer.MyOfferViewModel
import com.droppyn.ui.shoepicker.ActionBottom
import com.droppyn.ui.shoepicker.ShareDataShoeViewModel

class AddOfferFragment : Fragment() {

    private lateinit var binding: FragmentAddOfferBinding

    private val addOfferViewModel: AddOfferViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, AddOfferViewModel.Factory(activity.application)).get(AddOfferViewModel::class.java)
    }

    private val shareDataShoeViewModel: ShareDataShoeViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentAddOfferBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.addOfferViewModel = addOfferViewModel


        addOfferViewModel.navToHome.observe(viewLifecycleOwner, { nav ->
            if(nav) {
                findNavController().navigate(R.id.action_addOfferFragment_to_navigation_home)
                addOfferViewModel.navigationToHomeFinished()
            }
        })

        shareDataShoeViewModel.item.observe(viewLifecycleOwner,{ shoe ->
            addOfferViewModel.setShoe(shoe)
//            Log.i("offer", shoe.model)

        })

        addOfferViewModel.showShoeBottomSheet.observe(viewLifecycleOwner, { bottomSheet ->
            if(bottomSheet){
                openShoesBottomSheet()
                addOfferViewModel.showShoeBottomSheetFinished()
            }
        })

        addOfferViewModel.sizeChart.observe(viewLifecycleOwner, { sizes ->

            binding.sizePicker.minValue = 0
            binding.sizePicker.maxValue = 0

            if(sizes.isNotEmpty()){
                binding.sizePicker.minValue = 0
                binding.sizePicker.maxValue = sizes.size - 1
                binding.sizePicker.displayedValues = sizes.map { size -> size.us.toString() + " US"}.toTypedArray()
                addOfferViewModel.setSize(0)
            }
        })

        binding.sizePicker.setOnValueChangedListener { picker, oldVal, newVal -> addOfferViewModel.setSize(newVal) }


        addOfferViewModel.price.observe(viewLifecycleOwner, { price ->
            addOfferViewModel.setPrice(price.toDouble())
        })

        addOfferViewModel.bio.observe(viewLifecycleOwner, { bio ->
            addOfferViewModel.setBio(bio)
        })


        return binding.root
    }

    private fun openShoesBottomSheet() {
        val selectShoesBottomSheet = ActionBottom.newInstance()
        activity?.supportFragmentManager?.let { selectShoesBottomSheet.show(it, ActionBottom.TAG) }
    }

}