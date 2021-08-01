package com.droppyn.ui.myoffer.addoffer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droppyn.R

class AddOfferFragment : Fragment() {

    companion object {
        fun newInstance() = AddOfferFragment()
    }

    private lateinit var viewModel: AddOfferViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_offer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddOfferViewModel::class.java)
        // TODO: Use the ViewModel
    }

}