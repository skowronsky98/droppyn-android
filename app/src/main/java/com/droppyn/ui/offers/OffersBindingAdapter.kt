package com.droppyn.ui.offers

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.domain.Offer
import com.droppyn.ui.home.MyOffersAdapter

@BindingAdapter("offersListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Offer>?){
    val adapter = recyclerView.adapter as OffersAdapter
    adapter.submitList(data)
}