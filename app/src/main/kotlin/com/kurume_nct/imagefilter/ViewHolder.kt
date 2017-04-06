package com.kurume_nct.imagefilter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by hanah on 4/3/2017.
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textView: TextView
    //var imageView : ImageView

    init {
        textView = itemView.findViewById(R.id.text) as TextView
    //    imageView = itemView.findViewById(R.id.imageView) as ImageView
    }
}