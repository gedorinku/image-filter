package com.kurume_nct.imagefilter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_list_list_framgnet, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val array : ArrayList<String> = ArrayList(3)
        array.add("A")
        array.add("B")
        array.add("C")

        adapter = RecyclerViewAdapter(array);
        recyclerView.adapter = adapter

    }

    companion object {
        fun newInstance(): ListListFragment {
            val fragment = ListListFragment()
            return fragment
        }
    }

}

