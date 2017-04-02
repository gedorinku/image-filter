package com.kurume_nct.imagefilter

import android.view.View

/**
 * Created by hanah on 4/2/2017.
 */
interface OnRecyclerListner {
    fun onRecyclerClicked(view: View, position: Int) : Unit
}