package com.droppyn.ui.myoffer

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
import com.droppyn.databinding.FragmentMyOfferBinding
import com.droppyn.domain.Offer
import com.droppyn.uitl.ShareDataViewModel

class MyOfferFragment : Fragment() {

    companion object {
        fun newInstance() = MyOfferFragment()
    }

    private lateinit var binding: FragmentMyOfferBinding

//    private lateinit var myOfferViewModel: MyOfferViewModel

    private val myOfferViewModel: MyOfferViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, MyOfferViewModel.Factory(activity.application)).get(MyOfferViewModel::class.java)
    }

    private val shareDataViewModel: ShareDataViewModel<Offer> by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        myOfferViewModel = ViewModelProvider(this).get(MyOfferViewModel::class.java)


        binding = FragmentMyOfferBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.myOfferViewModel = myOfferViewModel



        shareDataViewModel.item.observe(viewLifecycleOwner, { offer ->
            myOfferViewModel.setMyOffer(offer)
        })


        myOfferViewModel.sizeChart.observe(viewLifecycleOwner, { sizes ->
            binding.sizePicker.minValue = 0
            binding.sizePicker.maxValue = sizes.size - 1
            binding.sizePicker.displayedValues = sizes.map { size -> size.us.toString() + " US"}.toTypedArray()
        })

        binding.sizePicker.setOnValueChangedListener { picker, oldVal, newVal -> myOfferViewModel.setSize(newVal) }

        myOfferViewModel.navToHome.observe(viewLifecycleOwner,{ nav ->
            if(nav){
                findNavController().navigate(R.id.action_myOfferFragment_to_navigation_home)
                myOfferViewModel.navigationToHomeFinished()
            }
        })

        return binding.root
    }

}