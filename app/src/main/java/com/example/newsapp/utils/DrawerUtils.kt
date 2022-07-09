package com.example.newsapp.utils

import com.example.newsapp.R
import com.example.newsapp.model.DrawerItem

object DrawerUtils {

    fun getDrawerItems(): MutableList<DrawerItem> {
        return mutableListOf(
            DrawerItem("Explore", R.drawable.explore),
            DrawerItem("Live Chat", R.drawable.live),
            DrawerItem("Gallery", R.drawable.gallery),
            DrawerItem("Wish List", R.drawable.wishlist),
            DrawerItem("E-Magazine", R.drawable.e_magazine),
        )
    }
}