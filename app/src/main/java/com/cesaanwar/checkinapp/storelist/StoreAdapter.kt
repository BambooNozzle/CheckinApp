package com.cesaanwar.checkinapp.storelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cesaanwar.checkinapp.databinding.ItemStoreBinding
import com.cesaanwar.checkinapp.uimodel.StoreListUIModel

class StoreAdapter(
    private val viewModel: StoreListViewModel
): ListAdapter<StoreListUIModel, StoreAdapter.StoreViewHolder>(StoreDiffUtil()) {

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
        binding.root.setOnClickListener {
            viewModel.visitStore(item)
        }
    }

}

class StoreDiffUtil: DiffUtil.ItemCallback<StoreListUIModel>() {
    override fun areItemsTheSame(oldItem: StoreListUIModel, newItem: StoreListUIModel): Boolean {
        return oldItem.storeId == newItem.storeId
    }

    override fun areContentsTheSame(oldItem: StoreListUIModel, newItem: StoreListUIModel): Boolean {
        return oldItem == newItem
    }
}