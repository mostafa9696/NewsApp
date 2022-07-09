package com.example.newsapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val networkFormat = "yyyy-MM-dd"
    val presentationFormat = "MMMM dd, yyyy"

    val simpleDateFormat = SimpleDateFormat(networkFormat, Locale.getDefault())
    val date = simpleDateFormat.parse(this)
    val result = SimpleDateFormat(presentationFormat, Locale.getDefault())
    return result.format(date ?: return this)
}