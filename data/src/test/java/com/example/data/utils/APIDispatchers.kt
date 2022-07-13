package com.example.data.utils

import com.example.data.BuildConfig
import com.example.data.Constants
import com.google.common.io.Resources
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection

class APIsDispatchers : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/articles?source=${Constants.NEXT_WEB_SOURCE}&apiKey=${BuildConfig.API_KEY}" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/articles_next_web_source.json"))
            }
            "/articles?source=${Constants.ASSOCIATED_PRESS_SOURCE}&apiKey=${BuildConfig.API_KEY}" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/articles_associated_press_source.json"))
            }
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

    private fun getJson(path: String): String {
        val uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
