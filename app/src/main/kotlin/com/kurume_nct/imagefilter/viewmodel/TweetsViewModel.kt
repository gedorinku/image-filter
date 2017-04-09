package com.kurume_nct.imagefilter.viewmodel

import android.os.Bundle
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import twitter4j.Paging
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/04.
 */
class TweetsViewModel(private val callback: ICallback) {

    private val tweets: MutableList<Status> = mutableListOf()

    val tweetList: List<Status>
        get() = tweets

    private lateinit var statusProvider: IStatusProvider

    fun onCreateView(arguments: Bundle) {
        statusProvider = IStatusProvider.create(arguments)
        statusProvider
                .requestStatuses(Paging())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it ->
                    addTweets(it)
                }
    }

    fun onRefresh(onRefreshed: () -> Unit) {
        val paging = if (0 < tweets.size) {
            Paging(tweets[0].id)
        } else {
            Paging()
        }
        statusProvider
                .requestStatuses(paging)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    addTweets(it)
                    onRefreshed()
                })
    }

    private fun addTweets(items: List<Status>) {
        if (items.isEmpty()) return
        tweets.addAll(0, items)
        callback.onItemRangeInserted(0, items.size)
    }

    interface ICallback {

        fun onItemRangeInserted(positionStart: Int, itemCount: Int)
    }
}