package com.droppyn.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.databinding.ItemMyOfferBinding
import com.droppyn.domain.Offer

class MyOffersAdapter: ListAdapter<Offer,MyOffersAdapter.MyOfferPropertyViewHolder>(DiffCallback) {

        class MyOfferPropertyViewHolder (private val binding : ItemMyOfferBinding):
                RecyclerView.ViewHolder(binding.root){

            fun bind(offer: Offer){
                binding.property = offer
                binding.executePendingBindings()
            }
        }


        companion object DiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOffersAdapter.MyOfferPropertyViewHolder {
        return MyOfferPropertyViewHolder(ItemMyOfferBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyOffersAdapter.MyOfferPropertyViewHolder, position: Int) {
        val advertismentProperty = getItem(position)

        holder.bind(advertismentProperty)
    }

}
