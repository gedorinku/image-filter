package com.kurume_nct.imagefilter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.view.RecyclerViewAdapter
import java.util.*

class ListListFragment : Fragment(){

    lateinit private var adapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view : View = inflater.inflate(R.layout.fragment_list_list_framgnet, container, false)
        val recyclerView = view.findViewById(R.id.recycler_view)
        val array = mutableListOf("hu","na","hi")
        if(recyclerView is RecyclerView){
            val context = recyclerView.context
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter(array)
            if(adapter != null){
                Log.d("adpter","succecanble")
            }
            Log.d("aaaaaaaaaaaa","aaaaaaaaaaaaaaaa")
            recyclerView.adapter = adapter
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //TODO get lists from twitter
    }

    companion object {
        fun newInstance(): ListListFragment {
            val fragment = ListListFragment()
            return fragment
        }
    }

}

