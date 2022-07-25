package com.example.wallpapercatalog.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.wallpapercatalog.R
import com.example.wallpapercatalog.databinding.FragmentMultipleImagesInstallerBinding

class MultipleImagesInstallerFragment : Fragment() {

    private lateinit var binding: FragmentMultipleImagesInstallerBinding

    private val args: MultipleImagesInstallerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMultipleImagesInstallerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.exampleTV.text = args.selectedItems.joinToString(", ") { it.imageUrl }
        super.onViewCreated(view, savedInstanceState)
    }

}