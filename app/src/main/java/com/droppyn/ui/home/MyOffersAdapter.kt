package com.droppyn.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.databinding.ItemMyOfferBinding
import com.droppyn.domain.Offer

class MyOffersAdapter(val clickListener: MyOfferListener): ListAdapter<Offer,MyOffersAdapter.MyOfferPropertyViewHolder>(DiffCallback) {

        class MyOfferPropertyViewHolder (private val binding : ItemMyOfferBinding):
                RecyclerView.ViewHolder(binding.root){

            fun bind(offer: Offer, clickListener: MyOfferListener){
                binding.property = offer
                binding.clickListener = clickListener
                binding.executePendingBindings()
            }
        }


        companion object DiffCallback : DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id === newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOffersAdapter.MyOfferPropertyViewHolder {
        return MyOfferPropertyViewHolder(ItemMyOfferBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyOffersAdapter.MyOfferPropertyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

}

class MyOfferListener(val clickListener: (myOffer: Offer) -> Unit) {
    fun onClick(myOffer: Offer) = clickListener(myOffer)
}
