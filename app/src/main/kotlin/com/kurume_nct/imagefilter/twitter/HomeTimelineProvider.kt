package com.kurume_nct.imagefilter.twitter

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import twitter4j.ResponseList
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/06.
 */
class HomeTimelineProvider : IStatusProvider {

    override fun requestStatuses(): Single<ResponseList<Status>> =
            Single.fromCallable {
                val twitter = TwitterAuthorizer.twitter
                twitter.homeTimeline
            }.subscribeOn(Schedulers.io())
}