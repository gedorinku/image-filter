package com.kurume_nct.imagefilter.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.BR
import com.kurume_nct.imagefilter.R
import java.util.*

/**
* Created by hanah on 4/2/2017.
 * うみゃー
*/

class RecyclerViewAdapter(private val mUserList: MutableList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)
        fun getBinding(): ViewDataBinding = binding
    }

    override fun getItemCount(): Int = mUserList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //データせっと
        //TODO DataBinding に失敗したまま layput かどちらかがまずい
        holder.getBinding().setVariable(BR.userListModelView,mUserList[position])
        holder.getBinding().executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.list_lists, parent,false)
        return ItemViewHolder(v)
    }
}