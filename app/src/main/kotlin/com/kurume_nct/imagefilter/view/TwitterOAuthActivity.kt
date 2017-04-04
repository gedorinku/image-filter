package com.kurume_nct.imagefilter.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.kurume_nct.imagefilter.R
import com.kurume_nct.imagefilter.databinding.ActivityTwitterOauthBinding
import com.kurume_nct.imagefilter.twitter.TwitterAuthorizer
import com.kurume_nct.imagefilter.viewmodel.TwitterOAuthViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class TwitterOAuthActivity : AppCompatActivity(), TwitterOAuthViewModel.ICallback {

    private lateinit var viewModel: TwitterOAuthViewModel
    private lateinit var binding: ActivityTwitterOauthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_oauth)

        val callBackUri = getString(R.string.twitter_callback_uri)
        viewModel = TwitterOAuthViewModel(callBackUri, this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_twitter_oauth)
        binding.viewModel = viewModel
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.onNewIntent(this, intent)
    }

    override fun openUri(intent: Intent) = startActivity(intent)

    override fun onSuccessOAuth() {
        val twitter = TwitterAuthorizer.twitter
        Single.fromCallable {
            twitter.updateStatus("OAuthできたっぽい")
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, Throwable::printStackTrace)
        showToast("認証成功")

        finish()
    }

    override fun onErrorOAuth(throwable: Throwable) {
        showToast("認証失敗")
        throwable.printStackTrace()
    }

    private fun showToast(text: String) =
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
