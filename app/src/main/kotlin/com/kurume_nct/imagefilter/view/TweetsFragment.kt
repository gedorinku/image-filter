package com.kurume_nct.imagefilter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.twitter.IStatusProvider
import com.kurume_nct.imagefilter.viewmodel.TweetsViewModel

class TweetsFragment : Fragment(), TweetsViewModel.ICallback {

    private val viewModel: TweetsViewModel = TweetsViewModel(this)

    private lateinit var adapter: TweetRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tweet_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = TweetRecyclerViewAdapter(viewModel.tweets)
            recyclerView.adapter = adapter
        }
        viewModel.onCreateView(arguments)

        return view
    }

    override fun onItemInserted(index: Int) {
        adapter.notifyItemInserted(index)
    }

    companion object {

        fun newInstance(statusProviderType: IStatusProvider.ProviderType): TweetsFragment {
            val fragment = TweetsFragment()
            val args = Bundle()
            statusProviderType.writeToBundle(args)
            fragment.arguments = args
            return fragment
        }
    }
}
