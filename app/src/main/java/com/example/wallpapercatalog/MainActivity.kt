package com.example.wallpapercatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityViewModel.progressBarVisibility.observe(this) {
            binding.progressBar.visibility = it
        }
    }
}