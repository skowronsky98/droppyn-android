package com.droppyn.ui.shop

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.domain.Shoe

@BindingAdapter("shoesListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Shoe>?){
    val adapter = recyclerView.adapter as ShopAdapter
    adapter.submitList(data)
}