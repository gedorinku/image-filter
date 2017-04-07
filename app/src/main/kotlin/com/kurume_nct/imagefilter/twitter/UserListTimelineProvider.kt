package com.kurume_nct.imagefilter.twitter

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import twitter4j.Paging
import twitter4j.ResponseList
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/06.
 */
class UserListTimelineProvider(val listId: Long) : IStatusProvider {
    override fun requestStatuses(paging: Paging): Single<ResponseList<Status>> =
            Single.fromCallable {
                val twitter = TwitterAuthorizer.twitter
                twitter.getUserListStatuses(listId, paging)
            }.subscribeOn(Schedulers.io())
}