package com.example.wallpapercatalog.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.WallpaperManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.wallpapercatalog.databinding.FragmentImagePreviewBinding
import com.example.wallpapercatalog.di.appComponent
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import javax.inject.Inject

class ImagePreviewFragment : Fragment() {

    private lateinit var binding: FragmentImagePreviewBinding

    private val args: ImagePreviewFragmentArgs by navArgs()
    private var imageUrl: String? = null

    @Inject
    lateinit var picasso: Picasso

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageUrl = args.imageUrl

        picasso
            .load(imageUrl)
            .into(binding.wallpaperPreview)

        binding.fab.setOnClickListener {
            imageUrl?.let {
                AlertDialog.Builder(requireContext())
                    .setMessage("Are you sure want to set it as background?")
                    .setPositiveButton("Yes") { _, _ -> setWallpaper(it) }
                    .setNegativeButton("No") { _, _ -> }
                    .show()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setWallpaper(imageUrl: String) {
        var wpBitmap: Bitmap? = null
        Picasso.with(requireContext())
            .load(imageUrl)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    wpBitmap = bitmap
                }

                override fun onBitmapFailed(errorDrawable: Drawable?) {

                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }
            })
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        wallpaperManager.setBitmap(wpBitmap)
        Toast.makeText(requireContext(), "Wallpaper set!", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentImagePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

}