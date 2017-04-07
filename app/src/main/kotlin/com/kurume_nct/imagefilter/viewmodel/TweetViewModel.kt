package com.kurume_nct.imagefilter.viewmodel

import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/04.
 */
class TweetViewModel(status: Status) {

    val name: String = status.user.name

    val screenName: String = "@${status.user.screenName}"

    val text: String = status.text

    val createdAt: String = status.createdAt.toString()


    interface ICallback
}