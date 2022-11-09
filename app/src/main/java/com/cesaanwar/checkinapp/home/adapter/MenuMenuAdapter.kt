package com.cesaanwar.checkinapp.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cesaanwar.checkinapp.databinding.ItemMenuMenuBinding
import com.cesaanwar.checkinapp.home.MainViewModel
import com.cesaanwar.checkinapp.uimodel.MenuMenuUIModel

class MenuMenuAdapter(val viewModel: MainViewModel): ListAdapter<MenuMenuUIModel, MenuMenuAdapter.MenuMenuViewHolder>(MenudMenuDiffUtil()){

    class MenuMenuViewHolder(val binding: ItemMenuMenuBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MenuMenuViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMenuMenuBinding.inflate(layoutInflater, parent, false)

                return MenuMenuViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuMenuViewHolder {
        return MenuMenuViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MenuMenuViewHolder, position: Int) {
        val menu = getItem(position)
        val binding = holder.binding
        binding.tvTitle.text = menu.title
        Glide.with(holder.itemView)
            .load(menu.icon)
            .into(binding.ivIcon)
        holder.itemView.setOnClickListener {
            if (menu.isVisit) {
                viewModel.triggerVisit()
            }
        }
    }

}

class MenudMenuDiffUtil: DiffUtil.ItemCallback<MenuMenuUIModel>() {
    override fun areItemsTheSame(
        oldItem: MenuMenuUIModel,
        newItem: MenuMenuUIModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MenuMenuUIModel,
        newItem: MenuMenuUIModel
    ): Boolean {
        return oldItem == newItem
    }

}