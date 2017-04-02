package com.kurume_nct.imagefilter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

class ListListFramgnet : Fragment(), OnRecyclerListner{

    private val activity : Activity? = null
    private val mView : View = null!!
    private val fragmentListner : RecyclerFragmentListner
    private val recyclerView : RecyclerView
    private var adapter : RelativeAdapter

    interface RecyclerFragmentListner{
        fun onRecyclerEvent();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_list_list_framgnet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val array : ArrayList<String> = ArrayList()
        array.add("A")
        array.add("B")
        array.add("C")

        adapter = RelativeAdapter(activity!!,array,this);
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): ListListFramgnet {
            val fragment = ListListFramgnet()
            return fragment
        }
    }

    override fun onRecyclerClicked(view: View, position: Int) {
        //Cellulic treatment (=?onClick)
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

