package com.kurume_nct.imagefilter.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.kurume_nct.imagefilter.BR

/**
 * Created by hanah on 4/2/2017.
 */

class UserListModelView(sentence: String) : BaseObservable() {
    var sentence : String = sentence
        @Bindable
        get() = field
        set(value) {
        field = value
        notifyPropertyChanged(BR.sentence)
        }
    //TODO pictureURL
}