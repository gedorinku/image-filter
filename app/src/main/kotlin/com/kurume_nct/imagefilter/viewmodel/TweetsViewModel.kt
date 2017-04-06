package com.kurume_nct.imagefilter.viewmodel

import android.os.Bundle
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/04.
 */
class TweetsViewModel(private val callback: ICallback) {

    val tweets: MutableList<Status> = mutableListOf()

    fun onCreateView(arguments: Bundle) {
        IStatusProvider
                .create(arguments)
                .requestStatuses()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
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