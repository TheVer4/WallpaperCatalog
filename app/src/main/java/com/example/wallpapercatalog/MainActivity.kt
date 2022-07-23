package com.example.wallpapercatalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wallpapercatalog.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        navController?.let {
            setupActionBarWithNavController(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        with(menu?.findItem(R.id.settingsFragment)?.icon) {
            val color = if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) com.google.android.material.R.color.design_default_color_on_secondary else com.google.android.material.R.color.design_default_color_on_primary
            this?.let { DrawableCompat.wrap(it).setTint(ContextCompat.getColor(this@MainActivity, color)) }
            menu?.findItem(R.id.settingsFragment)?.icon = this
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return navController?.let { item.onNavDestinationSelected(it) } ?: false || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }

    fun showProgressBar(isRequired: Boolean) {
        binding.progressBar.visibility = if(isRequired) View.VISIBLE else View.GONE
    }

    fun switchAppTheme(themeId: Int) {
        AppCompatDelegate.setDefaultNightMode(themeId)
    }
}