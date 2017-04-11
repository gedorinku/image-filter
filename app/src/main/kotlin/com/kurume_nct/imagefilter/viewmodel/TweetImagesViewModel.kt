package com.kurume_nct.imagefilter.viewmodel

import android.os.Bundle
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import twitter4j.MediaEntity
import twitter4j.Paging
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/08.
 */
class TweetImagesViewModel(private val callback: ICallback) {

    private var lastStatus: Status? = null
    private val images: MutableList<MediaEntity> = mutableListOf()
    private lateinit var statusProvider: IStatusProvider

    val imageList: List<MediaEntity> = images

    fun onCreateView(arguments: Bundle) {
        statusProvider = IStatusProvider.Companion.create(arguments)
        statusProvider
                .requestStatuses(Paging())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it ->
                    addImages(it)
                }
    }

    fun onRefresh(onRefreshed: () -> Unit) {
        val paging = when (lastStatus) {
            null -> Paging()
            else -> Paging(lastStatus!!.id)
        }
        statusProvider
                .requestStatuses(paging)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it ->
                    addImages(it)
                    onRefreshed()
                }
    }

    private fun addImages(items: List<Status>) {
        if (items.isEmpty()) return
        lastStatus = items.first()
        val fetchedImages = items
                .flatMap { it.mediaEntities.toList() }
                .filter { it.type == "photo" }
        images.addAll(0, fetchedImages)
        callback.onItemRangeInserted(0, fetchedImages.size)
    }


    interface ICallback {

        fun onItemRangeInserted(positionStart: Int, itemCount: Int)
    }
}