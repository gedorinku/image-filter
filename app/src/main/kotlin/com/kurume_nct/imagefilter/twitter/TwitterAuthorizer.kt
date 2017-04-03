package com.kurume_nct.imagefilter.twitter

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.kurume_nct.imagefilter.BuildConfig
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by gedorinku on 2017/04/02.
 */
object TwitterAuthorizer {

    private const val PREFERENCES_NAME = "pref"
    private const val KEY_TOKEN = "token"
    private const val KEY_TOKEN_SECRET = "token_secret"

    var twitter: Twitter
        private set

    private var requestToken: RequestToken? = null

    init {
        val config = ConfigurationBuilder()
                .setOAuthConsumerKey(BuildConfig.CONSUMER_KEY)
                .setOAuthConsumerSecret(BuildConfig.CONSUMER_SECRET)
                .build()
        val factory = TwitterFactory(config)
        twitter = factory.instance
    }

    fun startAuthorizing(callbackUri: String,
                         openUri: (Intent) -> Unit,
                         onError: (Throwable) -> Unit) {
        Single.fromCallable {
            requestToken = twitter.getOAuthRequestToken(callbackUri)
            requestToken!!.authorizationURL
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            openUri(intent)
        }, onError)
    }

    fun storeAccessToken(context: Context,
                         data: Uri, onSuccess: () -> Unit,
                         onError: (Throwable) -> Unit) {
        if (requestToken == null) {
            throw IllegalStateException("Call startAuthorizing() first.")
        }
        val verifier = data.getQueryParameter("oauth_verifier")
        Single.fromCallable {
            val accessToken = twitter.getOAuthAccessToken(requestToken, verifier)
            val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(KEY_TOKEN, accessToken.token)
            editor.putString(KEY_TOKEN_SECRET, accessToken.tokenSecret)
            editor.commit()
            accessToken
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            twitter.oAuthAccessToken = it
            onSuccess()
        }, onError)
    }

    fun tryLoadAccessToken(context: Context, callback: (isSuccess: Boolean) -> Unit) {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        val token = preferences.getString(KEY_TOKEN, "")
        val tokenSecret = preferences.getString(KEY_TOKEN_SECRET, "")
        if (token.isEmpty() || tokenSecret.isEmpty()) {
            callback(false)
        }
        twitter.oAuthAccessToken = AccessToken(token, tokenSecret)

        Single.fromCallable {
            twitter.verifyCredentials()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            callback(true)
        }, {
            it.printStackTrace()
            callback(false)
        })
    }
}