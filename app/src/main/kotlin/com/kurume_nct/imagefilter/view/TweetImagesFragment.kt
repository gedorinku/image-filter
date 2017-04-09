package com.kurume_nct.imagefilter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import com.kurume_nct.imagefilter.viewmodel.TweetImagesViewModel

class TweetImagesFragment : Fragment(), TweetImagesViewModel.ICallback {

    private val viewModel: TweetImagesViewModel = TweetImagesViewModel(this)

    private lateinit var adapter: TweetImagesRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_tweetimage_list, container, false)

        val recyclerView = view.findViewById(R.id.list)
        if (recyclerView is RecyclerView) {
            val context = recyclerView.context
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = TweetImagesRecyclerViewAdapter(viewModel.imageList)
            recyclerView.adapter = adapter

            if (view is SwipeRefreshLayout) {
                view.setOnRefreshListener {
                    viewModel.onRefresh {
                        view.isRefreshing = false
                        (recyclerView.layoutManager as LinearLayoutManager).scrollToPosition(0)
                    }
                }
            }
        }

        viewModel.onCreateView(arguments)

        return view
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        adapter.notifyItemRangeInserted(positionStart, itemCount)
    }

    companion object {

        fun newInstance(providerType: IStatusProvider.ProviderType): TweetImagesFragment {
            val fragment = TweetImagesFragment()
            val args = Bundle()
            providerType.writeToBundle(args)
            fragment.arguments = args
            return fragment
        }
    }
}
