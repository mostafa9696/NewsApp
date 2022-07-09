package com.example.newsapp.utils

import androidx.annotation.StringRes
import com.example.newsapp.R
import java.net.UnknownHostException

internal object ExceptionHandler {

    @StringRes
    fun parse(t: Throwable): Int {
        return when (t) {
            is UnknownHostException -> R.string.check_internet_connection
            else -> R.string.error_occured
        }
    }

}
