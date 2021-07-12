package com.droppyn.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.domain.Offer

@BindingAdapter("myOffersListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Offer>?){
    val adapter = recyclerView.adapter as MyOffersAdapter
    adapter.submitList(data)
}