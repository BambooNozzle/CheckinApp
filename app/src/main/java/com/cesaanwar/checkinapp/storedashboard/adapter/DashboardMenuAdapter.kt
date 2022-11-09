package com.cesaanwar.checkinapp.storedashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cesaanwar.checkinapp.databinding.ItemDashboardMenuBinding
import com.cesaanwar.checkinapp.uimodel.DashboardMenuUIModel

class DashboardMenuAdapter(): ListAdapter<DashboardMenuUIModel, DashboardMenuAdapter.DashboardMenuViewHolder>(DashboardMenuDiffUtil()){

    class DashboardMenuViewHolder(val binding: ItemDashboardMenuBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): DashboardMenuViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDashboardMenuBinding.inflate(layoutInflater, parent, false)

                return DashboardMenuViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardMenuViewHolder {
        return DashboardMenuViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DashboardMenuViewHolder, position: Int) {
        val menu = getItem(position)
        val binding = holder.binding
        binding.tvTitle.text = menu.title
        Glide.with(holder.itemView)
            .load(menu.icon)
            .into(binding.ivIcon)
    }

}

class DashboardMenuDiffUtil: DiffUtil.ItemCallback<DashboardMenuUIModel>() {
    override fun areItemsTheSame(
        oldItem: DashboardMenuUIModel,
        newItem: DashboardMenuUIModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DashboardMenuUIModel,
        newItem: DashboardMenuUIModel
    ): Boolean {
        return oldItem == newItem
    }

}