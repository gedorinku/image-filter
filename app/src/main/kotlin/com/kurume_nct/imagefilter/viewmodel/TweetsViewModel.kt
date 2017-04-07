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

    val tweets: MutableList<Status> = mutableListOf()

    private lateinit var statusProvider: IStatusProvider

    fun onCreateView(arguments: Bundle) {
        statusProvider = IStatusProvider.create(arguments)
        statusProvider
                .requestStatuses(Paging())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    it.reversed().forEach {
                        tweets.add(0, it)
                        callback.onItemInserted(0)
                    }
                })
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
                    it.reversed().forEach {
                        tweets.add(0, it)
                        callback.onItemInserted(0)
                    }
                    onRefreshed()
                })
    }

    interface ICallback {

        fun onItemInserted(index: Int)
    }
}