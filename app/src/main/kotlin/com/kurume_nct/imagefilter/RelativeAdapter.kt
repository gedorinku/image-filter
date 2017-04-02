package com.kurume_nct.imagefilter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

/**
 * Created by hanah on 4/2/2017.
 */

class RelativeAdapter(context : Context, data : ArrayList<String>, listner: OnRecyclerListner) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater : LayoutInflater = null!!
    private val data : ArrayList<String>
    private val context :Context
    private val listener : OnRecyclerListner

    //初期化
    init {
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        //データ表示
        if(data.size > position) {
            if (holder != null) {
                holder.textView.setText(data.get(position))
            }
        }
        if(holder != null){
            holder.itemView.setOnClickListener { v -> listener.onRecyclerClicked(v, position) }
        }
    }

    override fun getItemCount(): Int {
        if(true){
            return data.size
        }else{
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.list_lists, parent, false))
    }
}
