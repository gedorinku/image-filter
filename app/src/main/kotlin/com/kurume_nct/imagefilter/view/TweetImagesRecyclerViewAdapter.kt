package com.kurume_nct.imagefilter.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.BR
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.viewmodel.TweetImageViewModel
import twitter4j.MediaEntity

class TweetImagesRecyclerViewAdapter(private val images: List<MediaEntity>)
    : RecyclerView.Adapter<TweetImagesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_tweetimage, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = TweetImageViewModel(images[position])
        holder.binding.setVariable(BR.viewModel, viewModel)
        viewModel.onBindView(holder.view)
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val binding: ViewDataBinding = DataBindingUtil.bind(view)
    }
}
