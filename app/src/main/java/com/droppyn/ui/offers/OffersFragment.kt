package com.droppyn.ui.offers

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.droppyn.databinding.FragmentsOffersBinding
import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe
import com.droppyn.uitl.ShareDataViewModel

class OffersFragment : Fragment() {

    private val offersViewModel: OffersViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, OffersViewModel.Factory(activity.application)).get(OffersViewModel::class.java)
    }
    private lateinit var binding: FragmentsOffersBinding
    private val shareDataOfShoeViewModel: ShareDataViewModel<Shoe> by activityViewModels()
    private val shareDataOfOfferViewModel: ShareDataViewModel<Offer> by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentsOffersBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.offersViewModel = offersViewModel



        //TODO Pass offer to OfferDetailFragment
        binding.offersRecyclerView.adapter = OffersAdapter(OfferListener { offer ->
//            listOfOfferViewModel.setItem(offer)

            Toast.makeText(context,offer.id,Toast.LENGTH_SHORT).show()
        })

        shareDataOfShoeViewModel.item.observe(viewLifecycleOwner, { shoe ->
            offersViewModel.setFilter(shoe)
        })




        return binding.root
    }


}