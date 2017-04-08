package com.kurume_nct.imagefilter.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.BR
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.viewmodel.TweetViewModel
import twitter4j.Status

class TweetRecyclerViewAdapter(
        private val tweets: List<Status>)
    : RecyclerView.Adapter<TweetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_tweet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, TweetViewModel(tweets[position]))
    }

    override fun getItemCount(): Int = tweets.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: ViewDataBinding = DataBindingUtil.bind(view)
    }
}
