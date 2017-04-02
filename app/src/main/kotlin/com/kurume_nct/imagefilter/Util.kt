package com.kurume_nct.imagefilter

import android.databinding.Bindable
import android.graphics.Picture
import android.icu.lang.UCharacter
import android.widget.ImageView

/**
 * Created by hanah on 4/2/2017.
 */
class Util {

    @Bindable
    private var picture: ImageView
    @Bindable
    private var sentence : String

    constructor(picture: ImageView, sentence : String){
        this.picture = picture;
        this.sentence = sentence;
    }

    fun getPicture() : ImageView = this.picture

    fun setPicture(setPicture : ImageView) : Unit{
        this.picture = setPicture;

    }

    fun getSentence() : String = this.sentence

    fun setSentence(setSentence : String) : Unit{
        this.sentence = setSentence;
    }
}