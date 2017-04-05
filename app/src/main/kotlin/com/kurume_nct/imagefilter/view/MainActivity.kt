package com.kurume_nct.imagefilter.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(),
        MainViewModel.Callback {

    private val viewModel = MainViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume(this)
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    override fun startOAuthActivity() {
        val intent = Intent(this, TwitterOAuthActivity::class.java)
        startActivity(intent)
    }

    override fun openTimeLine() {
        replaceFragment(TweetsFragment.newInstance())
    }
}
