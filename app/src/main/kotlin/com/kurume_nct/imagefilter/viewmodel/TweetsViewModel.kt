package com.kurume_nct.imagefilter.viewmodel

import com.kurume_nct.imagefilter.twitter.TwitterAuthorizer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/04.
 */
class TweetsViewModel(private val callback: ICallback) {

    val tweets: MutableList<Status> = mutableListOf()

    fun onCreateView() {
        val twitter = TwitterAuthorizer.twitter

        Single.fromCallable {
            twitter.timelines().homeTimeline
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            it ->
            it.reversed().forEach {
                tweets.add(0, it)
                callback.onItemInserted(0)
            }
        })
    }

    interface ICallback {

        fun onItemInserted(index: Int)
    }
}