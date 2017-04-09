package com.kurume_nct.imagefilter.view

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by gedorinku on 2017/04/08.
 */
@BindingAdapter("bind:imageUri")
fun onLoadImage(view: ImageView, uri: String) {
    Glide.with(view.context).load(Uri.parse(uri)).into(view)
}
