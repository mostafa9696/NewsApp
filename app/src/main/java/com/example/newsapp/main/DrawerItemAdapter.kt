package com.example.newsapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemDrawerMenuBinding
import com.example.newsapp.model.DrawerItem

class DrawerItemAdapter(
    var drawerItems: List<DrawerItem>,
    var onItemClick: (item: DrawerItem) -> Unit
) :
    RecyclerView.Adapter<DrawerItemAdapter.DrawerItemViewHolder>() {

    var selectedItemPosition: Int = -1

    inner class DrawerItemViewHolder(var binding: ItemDrawerMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drawerItem: DrawerItem) {
            binding.apply {
                ivNavItem.setImageResource(drawerItem.iconResource)
                tvNavItem.text = drawerItem.name
                ivSelector.visibility = if (drawerItem.isSelected) View.VISIBLE else View.GONE
            }

            binding.root.setOnClickListener {
                if(selectedItemPosition != -1) {
                    drawerItems[selectedItemPosition].isSelected = false
                    notifyItemChanged(selectedItemPosition)
                }
                drawerItems[adapterPosition].isSelected = true
                notifyItemChanged(adapterPosition)
                selectedItemPosition = adapterPosition
                onItemClick.invoke(drawerItems[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DrawerItemAdapter.DrawerItemViewHolder {
        val binding =
            ItemDrawerMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DrawerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrawerItemAdapter.DrawerItemViewHolder, position: Int) {
        holder.bind(drawerItems[position])
    }

    override fun getItemCount(): Int {
        return drawerItems.size
    }
}