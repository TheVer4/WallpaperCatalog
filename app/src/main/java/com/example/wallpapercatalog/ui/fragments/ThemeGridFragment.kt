package com.example.wallpapercatalog.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpapercatalog.databinding.FragmentThemeGridBinding
import com.example.wallpapercatalog.di.ViewModelFactory
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter
import com.example.wallpapercatalog.ui.model.UiState
import com.example.wallpapercatalog.ui.viewModels.ActivityViewModel
import com.example.wallpapercatalog.ui.viewModels.ThemeViewModel
import javax.inject.Inject

class ThemeGridFragment : Fragment() {

    private lateinit var binding: FragmentThemeGridBinding

    private val args: ThemeGridFragmentArgs by navArgs()
    private var themeId: Int? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ThemeViewModel> { viewModelFactory }
    private val activityViewModel by viewModels<ActivityViewModel> { viewModelFactory }

    private val adapter = WallpaperGridAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        themeId = args.themeId

        themeId?.let {
            viewModel.loadValues(it)
        }

        binding.themeRecycler.adapter = adapter
        binding.themeRecycler.layoutManager = GridLayoutManager(requireContext(), 3)

        viewModel.liveData.observe(viewLifecycleOwner) {
            it ?: return@observe
            activityViewModel.showProgressBar(it is UiState.Loading)

            when(it) {
                is UiState.Success -> adapter.submitList(it.value)
                is UiState.Error -> {
                    activityViewModel.showProgressBar(false)
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                }
                else -> return@observe
            }
        }

        adapter.setOnItemClickListener { _, holder ->
            holder.item?.imageUrl?.let {
                ThemeGridFragmentDirections.actionThemeGridFragmentToImagePreviewFragment(it).also { navDir ->
                    findNavController().navigate(navDir)
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentThemeGridBinding.inflate(inflater, container, false)
        return binding.root
    }
}