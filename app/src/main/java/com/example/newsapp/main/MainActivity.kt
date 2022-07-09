package com.example.newsapp.main

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.data.remote.ApisService
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.utils.DrawerUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apisService: ApisService
    lateinit var binding: ActivityMainBinding
    lateinit var drawerItemAdapter: DrawerItemAdapter
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initNavContorller()
        initDrawerMenuView()
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