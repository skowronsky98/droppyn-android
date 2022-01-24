package com.droppyn.ui.addoffer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentAddOfferBinding
import com.droppyn.domain.Shoe
import com.droppyn.ui.addoffer.item.ShoeButtonFragment
import com.droppyn.ui.addoffer.item.ShoeDataFragment
import com.droppyn.ui.addoffer.item.ShoeItemFragment
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

        val infoFragment = ShoeDataFragment()

        var fragment: Fragment = ShoeButtonFragment()
        viewFragment(R.id.fragment_container_view, fragment)

        shareDataShoeViewModel.active.observe(viewLifecycleOwner, {active ->
            if(active){
                if(binding.fragmentContainerViewModel.isEmpty()){
                    viewFragment(R.id.fragment_container_view_model, infoFragment)

                    disableFragment(fragment)
                    fragment = ShoeItemFragment()
                    viewFragment(R.id.fragment_container_view, fragment)
                }
            }
        })

        addOfferViewModel.navToHome.observe(viewLifecycleOwner, { nav ->
            if(nav) {

                shareDataShoeViewModel.desacite()

                findNavController().popBackStack()
                addOfferViewModel.navigationToHomeFinished()
            }
        })

        shareDataShoeViewModel.item.observe(viewLifecycleOwner,{ shoe ->
            addOfferViewModel.setShoe(shoe)
//            disableFragment(fragment)
//            fragment = ShoeItemFragment()
//            viewFragment(fragment)



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

    private fun viewFragment(id: Int, fragment: Fragment){
        childFragmentManager.commit {
            add(id, fragment)
            setReorderingAllowed(true)
        }
    }

    private fun disableFragment(fragment: Fragment){
        childFragmentManager.beginTransaction().remove(fragment).commit()
    }

    private fun openShoesBottomSheet() {
        val selectShoesBottomSheet = ActionBottom.newInstance()
        activity?.supportFragmentManager?.let { selectShoesBottomSheet.show(it, ActionBottom.TAG) }
    }

}