package com.kurume_nct.imagefilter.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.DrawableRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import com.kurume_nct.imagefilter.R
import twitter4j.MediaEntity
import java.lang.Exception

/**
 * Created by gedorinku on 2017/04/08.
 */
class TweetImageViewModel(image: MediaEntity) : BaseObservable() {

    val imageUri: String = image.mediaURLHttps

    var isLoading: Boolean = true
        @Bindable
        get
        private set

    fun onBindView(view: View) {
        val imageView = view.findViewById(R.id.image_view) as ImageView
        val progressBar = view.findViewById(R.id.progress_bar) as ProgressBar
        Glide.with(view.context).load(Uri.parse(imageUri)).listener(object : RequestListener<Uri, GlideDrawable> {
            override fun onException(e: Exception?, model: Uri?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: GlideDrawable?, model: Uri?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        }).into(imageView)
    }
}
