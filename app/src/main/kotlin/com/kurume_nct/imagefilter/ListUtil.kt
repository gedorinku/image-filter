package com.kurume_nct.imagefilter

import android.widget.ImageView

/**
 * Created by hanah on 4/2/2017.
 */
class ListUtil(private var picture: ImageView) {

    fun getPicture() : ImageView{
        return this.picture
    }

    fun setPicture(setPicture : ImageView){
        this.picture = setPicture;
    }
}