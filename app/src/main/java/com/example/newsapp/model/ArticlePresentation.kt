package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlePresentation(
    val authorName: String? = null,
    val title: String? = null,
    val description: String? = null,
    val websiteUrl: String? = null,
    val imageUrl: String? = null,
    val publishedAt: String? = null
): Parcelable