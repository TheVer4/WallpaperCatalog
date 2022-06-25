package com.example.wallpapercatalog.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpapercatalog.R
import com.example.wallpapercatalog.databinding.FragmentMainGridBinding
import com.example.wallpapercatalog.di.ViewModelFactory
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter
import com.example.wallpapercatalog.ui.model.UiState
import com.example.wallpapercatalog.ui.viewModels.ActivityViewModel
import com.example.wallpapercatalog.ui.viewModels.MainViewModel
import javax.inject.Inject


class MainGridFragment : Fragment() {

    private lateinit var binding: FragmentMainGridBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory }
    private val activityViewModel by viewModels<ActivityViewModel> { viewModelFactory }

    private val adapter = WallpaperGridAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainRecycler.adapter = adapter
        binding.mainRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.liveData.observe(viewLifecycleOwner) {
            it ?: return@observe
            activityViewModel.showProgressBar(it is UiState.Loading)

            if(it is UiState.Success)
                adapter.submitList(it.value)
        }

        adapter.setOnItemClickListener { _, holder ->
            holder.item?.id?.let {
                MainGridFragmentDirections.actionMainGridFragmentToThemeGridFragment(it, holder.item?.title ?: getString(
                                    R.string.details_default_header)).also { navDir ->
                    findNavController().navigate(navDir)
                }
            }
        }

    }
}