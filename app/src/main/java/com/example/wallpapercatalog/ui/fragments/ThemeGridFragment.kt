package com.example.wallpapercatalog.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpapercatalog.MainActivity
import com.example.wallpapercatalog.databinding.FragmentThemeGridBinding
import com.example.wallpapercatalog.di.ViewModelFactory
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.di.showLongToast
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter
import com.example.wallpapercatalog.ui.model.GridItems
import com.example.wallpapercatalog.ui.model.UiState
import com.example.wallpapercatalog.ui.viewModels.ThemeViewModel
import javax.inject.Inject

class ThemeGridFragment : Fragment() {

    private lateinit var binding: FragmentThemeGridBinding

    private val args: ThemeGridFragmentArgs by navArgs()
    private var themeId: Int? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<ThemeViewModel> { viewModelFactory }
    private var activity: Activity? = null

    private val adapter = WallpaperGridAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        activity = if (requireActivity() is MainActivity) requireActivity() else null
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        themeId = args.themeId

        themeId?.let {
            viewModel.loadValues(it)
        }

        with(binding.themeRecycler) {
            this.adapter = this@ThemeGridFragment.adapter
            this.layoutManager = GridLayoutManager(requireContext(), 3)
        }

        viewModel.liveData.observe(viewLifecycleOwner) {
            it ?: return@observe
            (activity as? MainActivity)?.showProgressBar(it is UiState.Loading)

            when(it) {
                is UiState.Success -> adapter.submitList(it.value)
                is UiState.Error -> {
                    (activity as? MainActivity)?.showProgressBar(false)
                    requireContext().showLongToast(it.msg)
                }
                else -> return@observe
            }
        }

        with(adapter) {
            setOnItemClickListener { _, holder ->
                holder.item?.imageUrl?.let {
                    ThemeGridFragmentDirections.actionThemeGridFragmentToImagePreviewFragment(it).also { navDir ->
                        findNavController().navigate(navDir)
                    }
                }
            }

            setOnMultiSelectionModeChangedListener { state ->
                binding.continueWithSelectedButton.visibility = if(state) View.VISIBLE else View.GONE
            }
        }

        binding.continueWithSelectedButton.setOnClickListener {
            val items = (viewModel.liveData.value as? UiState.Success)?.value
            val gridItems = GridItems()
            items?.filter { it.selected }?.let { it1 -> gridItems.addAll(it1) }
            ThemeGridFragmentDirections.actionThemeGridFragmentToMultipleImagesInstallerFragment(gridItems).also { navDir ->
                findNavController().navigate(navDir)
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