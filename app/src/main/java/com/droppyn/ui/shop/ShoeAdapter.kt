package com.droppyn.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droppyn.databinding.ItemShoeBinding
import com.droppyn.domain.Shoe

class ShoeAdapter(val clickListener: ShoeListener): ListAdapter<Shoe, ShoeAdapter.ShoePropertyViewHolder>(DiffCallback) {

    class ShoePropertyViewHolder (private val binding : ItemShoeBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(shoe: Shoe, clickListener: ShoeListener){
            binding.property = shoe

            Glide.with(binding.offerImage)
                .load(shoe.media.imageUrl)
                .into(binding.offerImage)

            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Shoe>() {
        override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeAdapter.ShoePropertyViewHolder {
        return ShoePropertyViewHolder(ItemShoeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ShoeAdapter.ShoePropertyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

}

class ShoeListener(val clickListener: (shoe: Shoe) -> Unit) {
    fun onClick(shoe: Shoe) = clickListener(shoe)
}
