package com.cesaanwar.checkinapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cesaanwar.checkinapp.databinding.ItemMenuStatisticsBinding
import com.cesaanwar.checkinapp.uimodel.MenuStatisticsUIModel

class MenuStatisticsAdapter(): ListAdapter<MenuStatisticsUIModel, MenuStatisticsAdapter.MenuStatisticsViewHolder>(MenuStatisticsDiffUtil()){

    class MenuStatisticsViewHolder(val binding: ItemMenuStatisticsBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MenuStatisticsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMenuStatisticsBinding.inflate(layoutInflater, parent, false)

                return MenuStatisticsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuStatisticsViewHolder {
        return MenuStatisticsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MenuStatisticsViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        binding.menuStatisticsUIModel = item
        Glide.with(holder.itemView)
            .load(item.icon)
            .into(binding.imageView)
    }

}

class MenuStatisticsDiffUtil: DiffUtil.ItemCallback<MenuStatisticsUIModel>() {
    override fun areItemsTheSame(
        oldItem: MenuStatisticsUIModel,
        newItem: MenuStatisticsUIModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MenuStatisticsUIModel,
        newItem: MenuStatisticsUIModel
    ): Boolean {
        return oldItem == newItem
    }


}