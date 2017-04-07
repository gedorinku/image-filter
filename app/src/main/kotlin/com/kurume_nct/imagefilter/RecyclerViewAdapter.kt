package com.kurume_nct.imagefilter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
* Created by hanah on 4/2/2017.
*/

class RecyclerViewAdapter(list: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val mUserList : ArrayList<String> = list

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)
        fun getBinding(): ViewDataBinding = binding
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //データせっと
        holder.getBinding().setVariable(BR.util,mUserList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_list_framgnet, parent,false)
        return ItemViewHolder(v)
    }
}