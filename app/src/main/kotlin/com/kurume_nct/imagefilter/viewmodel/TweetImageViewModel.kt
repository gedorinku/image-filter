package com.kurume_nct.imagefilter.viewmodel

import android.databinding.BaseObservable
import twitter4j.MediaEntity

/**
 * Created by gedorinku on 2017/04/08.
 */
class TweetImageViewModel(image: MediaEntity) : BaseObservable() {

    val imageUri: String = image.mediaURLHttps
}
