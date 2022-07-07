package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.Constants
import com.example.data.remote.ApisService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apisService: ApisService

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("vv9", "error " + exception.message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(coroutineExceptionHandler) {
            val v = apisService.getNewsArticles(Constants.ASSOCIATED_PRESS_SOURCE)
            Log.d("vv9", v.toString())
        }

        lifecycleScope.launch(coroutineExceptionHandler) {
            val v = apisService.getNewsArticles(Constants.NEXT_WEB_SOURCE)
            Log.d("vv9", v.toString())
        }
    }
}