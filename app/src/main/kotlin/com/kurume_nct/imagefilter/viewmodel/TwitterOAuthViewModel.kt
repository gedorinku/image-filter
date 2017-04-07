package com.kurume_nct.imagefilter.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import com.kurume_nct.imagefilter.twitter.TwitterAuthorizer

/**
 * Created by gedorinku on 2017/04/02.
 */
class TwitterOAuthViewModel(
        private val callbackUri: String,
        private val callback: ICallback) {

    fun startAuthorize(view: View) =
            TwitterAuthorizer.startAuthorizing(
                    callbackUri,
                    callback::openUri,
                    callback::onErrorOAuth)

    fun onNewIntent(context: Context, intent: Intent?) {
        if (intent == null ||
                intent.data == null ||
                !intent.data.toString().startsWith(callbackUri)) {
            return
        }

        TwitterAuthorizer.storeAccessToken(
                context,
                intent.data,
                callback::onSuccessOAuth,
                callback::onErrorOAuth)
    }

    interface ICallback {

        fun openUri(intent: Intent)

        fun onSuccessOAuth()

        fun onErrorOAuth(throwable: Throwable)
    }
}