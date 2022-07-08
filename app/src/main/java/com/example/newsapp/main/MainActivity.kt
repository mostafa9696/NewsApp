package com.example.newsapp.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.data.Constants
import com.example.data.remote.ApisService
import com.example.newsapp.DrawerUtils
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apisService: ApisService
    lateinit var binding: ActivityMainBinding
    lateinit var drawerItemAdapter: DrawerItemAdapter
    lateinit var appBarConfiguration: AppBarConfiguration

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("vv9", "error " + exception.message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initNavContorller()
        initDrawerMenuView()
        lifecycleScope.launch(coroutineExceptionHandler) {
            val v = apisService.getNewsArticles(Constants.ASSOCIATED_PRESS_SOURCE)
            Log.d("vv9", v.toString())
        }

        lifecycleScope.launch(coroutineExceptionHandler) {
            val v = apisService.getNewsArticles(Constants.NEXT_WEB_SOURCE)
            Log.d("vv9", v.toString())
        }
    }

    private fun initNavContorller() {
        val navController = findNavController(R.id.nav_container)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.newsFragment), binding.navDrawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initDrawerMenuView() {
        drawerItemAdapter = DrawerItemAdapter(DrawerUtils.getDrawerItems()) {
            binding.navDrawer.close()
            Toast.makeText(this, it.name, Toast.LENGTH_LONG).show()
        }
        binding.navigationRv.adapter = drawerItemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}