package com.example.newsapp.utils

import java.net.UnknownHostException

internal object ExceptionHandler {

    fun parse(t: Throwable): String {
        return when (t) {
            is UnknownHostException -> "Check your internet connection"
            else -> t.message.toString()
        }
    }

}
