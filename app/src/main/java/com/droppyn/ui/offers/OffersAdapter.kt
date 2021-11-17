package com.droppyn.ui.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droppyn.databinding.ItemOfferBinding
import com.droppyn.domain.Offer

class OffersAdapter(val clickListener: OfferListener): ListAdapter<Offer, OffersAdapter.OfferPropertyViewHolder>(DiffCallback) {

    class OfferPropertyViewHolder (private val binding : ItemOfferBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(offer: Offer, clickListener: OfferListener){
            binding.property = offer
            binding.clickListener = clickListener
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.OfferPropertyViewHolder {
        return OfferPropertyViewHolder(ItemOfferBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OffersAdapter.OfferPropertyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


}

class OfferListener(val clickListener: (offer: Offer) -> Unit) {
    fun onClick(offer: Offer) = clickListener(offer)
}