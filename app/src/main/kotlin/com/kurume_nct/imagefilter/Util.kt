package com.kurume_nct.imagefilter

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.widget.ImageView

/**
 * Created by hanah on 4/2/2017.
 */

class Util : BaseObservable() {

    //lateinit private var picture: ImageView
    lateinit private var sentence : String

    //@Bindable
    //fun getPicture() = this.picture

    @Bindable
    fun getSentence() = this.sentence

   /* fun setSentence(setPicture : ImageView){
        this.picture = setPicture
        notifyPropertyChanged(BR.picture)
    }*/

    fun setSentence(setSentence : String) : Unit{
        this.sentence = setSentence
        notifyPropertyChanged(BR.sentence)
    }
}