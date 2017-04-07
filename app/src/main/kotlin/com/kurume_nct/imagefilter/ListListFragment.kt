package com.kurume_nct.imagefilter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

class ListListFragment : Fragment(){

    lateinit private var recyclerView : RecyclerView
    lateinit private var adapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view : View = inflater.inflate(R.layout.fragment_list_list_framgnet, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val array : ArrayList<String> = ArrayList(3)
        array.add("A")
        array.add("B")
        array.add("C")

        Log.d("List","add")
        recyclerView = RecyclerView(context)
        adapter = RecyclerViewAdapter(array)
        recyclerView.adapter = adapter

    }

    companion object {
        fun newInstance(): ListListFragment {
            val fragment = ListListFragment()
            return fragment
        }
    }

}

