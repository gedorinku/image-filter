package com.kurume_nct.imagefilter.viewmodel

import android.content.Context
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import com.kurume_nct.imagefilter.twitter.TwitterAuthorizer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by gedorinku on 2017/04/03.
 */
class MainViewModel(private val callback: ICallback) {

    fun onResume(context: Context) {
        TwitterAuthorizer.tryLoadAccessToken(context, {
            if (!it) {
                callback.startOAuthActivity()
            } else {
                Single.fromCallable {
                    val twitter = TwitterAuthorizer.twitter
                    twitter.getUserLists("gedorinku")
                }.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            it ->
                            callback.openTimeLine(
                                    IStatusProvider
                                            .ProviderType
                                            .HOME_TIMELINE_TYPE
                            )
                        })
            }
        })
    }

    interface ICallback {

        fun startOAuthActivity()

        fun openTimeLine(providerType: IStatusProvider.ProviderType)
    }
}
