package com.example.newsapp.model

data class DrawerItem(
    val name: String,
    val iconResource: Int,
    var isSelected: Boolean = false
)