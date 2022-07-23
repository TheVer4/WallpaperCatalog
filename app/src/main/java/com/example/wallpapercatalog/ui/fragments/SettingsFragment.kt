package com.example.wallpapercatalog.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.wallpapercatalog.MainActivity
import com.example.wallpapercatalog.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private var activity: Activity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.themeSwitch.isChecked =
            AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO
        binding.themeSwitch.setOnClickListener {
            (activity as? MainActivity)?.switchAppTheme(if (binding.themeSwitch.isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        activity = if (requireActivity() is MainActivity) requireActivity() else null
        super.onAttach(context)
    }

}