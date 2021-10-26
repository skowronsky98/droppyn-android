package com.droppyn.ui.offers.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentFilterBinding
import com.droppyn.ui.shoepicker.ShareDataShoeViewModel

class FilterFragment : Fragment() {

    private val filterViewModel: FilterViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, FilterViewModel.Factory(activity.application)).get(FilterViewModel::class.java)
    }

    private lateinit var binding: FragmentFilterBinding

    private val shareFilterDataViewModel: ShareFilterDataViewModel by activityViewModels()


     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentFilterBinding.inflate(inflater, container, false)
         binding.lifecycleOwner = this
         binding.filterViewModel = filterViewModel

         filterViewModel.navToHome.observe(viewLifecycleOwner,  { nav ->
             if(nav){
                 findNavController().navigate(R.id.action_filterFragment_to_offersFragment)
                 filterViewModel.navigationToHomeFinished()
             }
         })


         filterViewModel.applyFilter.observe(viewLifecycleOwner, { filterClicked ->
             if(filterClicked) {
                 filterViewModel.size.value?.let { shareFilterDataViewModel.setItem(it) }
                 filterViewModel.applyFilterFinished()
             }
         })

         filterViewModel.removeFilter.observe(viewLifecycleOwner, { removeFilter ->
             if(removeFilter) {
                 shareFilterDataViewModel.clearData()
                 filterViewModel.removeFilterFinished()
             }
         })

         filterViewModel.sizeChart.observe(viewLifecycleOwner, { sizes ->
             binding.sizePicker.minValue = 0
             binding.sizePicker.maxValue = sizes.size - 1
             binding.sizePicker.displayedValues = sizes.map { size -> size.us.toString() + " US"}.toTypedArray()
             filterViewModel.setSize(0)
         })

         binding.sizePicker.setOnValueChangedListener { picker, oldVal, newVal -> filterViewModel.setSize(newVal) }

        return binding.root
    }


}