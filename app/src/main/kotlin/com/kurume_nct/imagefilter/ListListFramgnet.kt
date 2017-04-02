package com.kurume_nct.imagefilter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListListFramgnet : Fragment(), OnRecyclerListner{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_list_list_framgnet, container, false)
    }

    companion object {
        fun newInstance(): ListListFramgnet {
            val fragment = ListListFramgnet()
            return fragment
        }
    }

    override fun onRecyclerClicked(view: View, position: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

