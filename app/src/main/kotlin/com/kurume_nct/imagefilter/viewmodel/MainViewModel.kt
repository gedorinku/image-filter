package com.kurume_nct.imagefilter.viewmodel

import android.content.Context
import com.kurume_nct.imagefilter.twitter.TwitterAuthorizer

/**
 * Created by gedorinku on 2017/04/03.
 */
class MainViewModel(private val callback: Callback) {

    fun onCreate(context: Context) {
        TwitterAuthorizer.tryLoadAccessToken(context, {
            if (!it) callback.startOAuthActivity()
        })
    }

    interface Callback {

        fun startOAuthActivity()
    }
}
