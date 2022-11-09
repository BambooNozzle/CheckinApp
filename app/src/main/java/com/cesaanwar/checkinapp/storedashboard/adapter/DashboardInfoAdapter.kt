package com.cesaanwar.checkinapp.storedashboard.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cesaanwar.checkinapp.databinding.ItemDashboardInfoBinding
import com.cesaanwar.checkinapp.uimodel.DashboardInfoUIModel

class DashboardInfoAdapter(): ListAdapter<DashboardInfoUIModel, DashboardInfoAdapter.DashboardInfoViewHolder>(DashboardInfoDiffUtil()){

    class DashboardInfoViewHolder(val binding: ItemDashboardInfoBinding): ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): DashboardInfoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDashboardInfoBinding.inflate(layoutInflater, parent, false)

                return DashboardInfoViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardInfoViewHolder {
        return DashboardInfoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DashboardInfoViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        binding.card.setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(item.color)))
    }

}

class DashboardInfoDiffUtil: DiffUtil.ItemCallback<DashboardInfoUIModel>() {
    override fun areItemsTheSame(
        oldItem: DashboardInfoUIModel,
        newItem: DashboardInfoUIModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DashboardInfoUIModel,
        newItem: DashboardInfoUIModel
    ): Boolean {
        return oldItem == newItem
    }


}