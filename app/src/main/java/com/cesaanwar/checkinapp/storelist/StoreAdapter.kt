package com.cesaanwar.checkinapp.storelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cesaanwar.checkinapp.data.Store
import com.cesaanwar.checkinapp.databinding.ItemStoreBinding
import com.cesaanwar.checkinapp.uimodel.StoreUIModel

class StoreAdapter: ListAdapter<StoreUIModel, StoreAdapter.StoreViewHolder>(StoreDiffUtil()) {

    class StoreViewHolder(val binding: ItemStoreBinding): ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): StoreViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemStoreBinding.inflate(layoutInflater, parent, false)

                return StoreViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position)
        binding.item = item
    }

}

class StoreDiffUtil: DiffUtil.ItemCallback<StoreUIModel>() {
    override fun areItemsTheSame(oldItem: StoreUIModel, newItem: StoreUIModel): Boolean {
        return oldItem.storeId == newItem.storeId
    }

    override fun areContentsTheSame(oldItem: StoreUIModel, newItem: StoreUIModel): Boolean {
        return oldItem == newItem
    }
}