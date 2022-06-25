package com.example.wallpapercatalog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wallpapercatalog.databinding.ActivityMainBinding
import com.example.wallpapercatalog.di.ViewModelFactory
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.ui.viewModels.ActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val activityViewModel by viewModels<ActivityViewModel> { viewModelFactory }

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityViewModel.progressBarVisibility.observe(this) {
            binding.progressBar.visibility = it
        }

        activityViewModel.appTheme.observe(this) {
            AppCompatDelegate.setDefaultNightMode(it)
            val intent = this.intent
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//            this.finish()
//            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        navController?.let {
            setupActionBarWithNavController(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navController?.let { item.onNavDestinationSelected(it) } ?: false || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }
}