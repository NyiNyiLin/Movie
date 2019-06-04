package com.nyi.movie.feature.recycle

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.nyi.movie.R

import kotlinx.android.synthetic.main.activity_recycle.*
import kotlinx.android.synthetic.main.content_recycle.*
import android.net.http.SslError
import android.util.Log
import android.webkit.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.SslErrorHandler
import android.content.Intent
import android.net.Proxy.getHost
import android.net.Uri
import android.view.View
import android.widget.Toast
import android.widget.FrameLayout
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.os.Message
import com.nyi.movie.feature.recycle.Recycle.UriChromeClient
import android.webkit.WebSettings

class Recycle : AppCompatActivity() {

    private val target_url = "https://business.recyclemm.com/"
    private val target_url_prefix = "business.recyclemm.com"
    private var mContext: Context? = this
    //private var mWebview: WebView? = null
    private var mWebviewPop: WebView? = null
    //private var mContainer: FrameLayout? = null
    private val mLastBackPressTime: Long = 0
    private val mToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


//        wv.getSettings().setJavaScriptEnabled(true)
//        wv.getSettings().setLoadWithOverviewMode(true)
//        wv.getSettings().setUseWideViewPort(true)
//        wv.setWebViewClient(object : WebViewClient() {
//
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//
//                view.loadUrl(url)
//
//                return true
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//
//            }
//        })
//        wv.loadUrl("https://business.recyclemm.com/")

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        //mWebview = findViewById<View>(R.id.webview) as WebView
        //mWebviewPop = (WebView) findViewById(R.id.webviewPop);
        //mContainer = findViewById<View>(R.id.webview_frame) as FrameLayout
        val webSettings = wv.settings
        webSettings.javaScriptEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.setSupportMultipleWindows(true)
        wv.webViewClient = UriWebViewClient()
        wv.webChromeClient = UriChromeClient()
        wv.loadUrl(target_url)

        mContext = this.applicationContext
    }

    override fun onBackPressed() {
        if (wv.isFocused() && wv.canGoBack()) {
            wv.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private inner class UriWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            val host = Uri.parse(url).getHost()
            //Log.d("shouldOverrideUrlLoading", url);
            if (host == target_url_prefix) {
                // This is my web site, so do not override; let my WebView load
                // the page
                if (mWebviewPop != null) {
                    mWebviewPop?.setVisibility(View.GONE)
                    frame?.removeView(mWebviewPop)
                    mWebviewPop = null
                }
                return false
            }

            if (host == "m.facebook.com") {
                return false
            }
            // Otherwise, the link is not for a page on my site, so launch
            // another Activity that handles URLs
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            return true
        }

        override fun onReceivedSslError(
            view: WebView, handler: SslErrorHandler,
            error: SslError
        ) {
            Log.d("onReceivedSslError", "onReceivedSslError")
            //super.onReceivedSslError(view, handler, error);
        }
    }

    internal inner class UriChromeClient : WebChromeClient() {


        override fun onCreateWindow(
            view: WebView, isDialog: Boolean,
            isUserGesture: Boolean, resultMsg: Message
        ): Boolean {
            mWebviewPop = WebView(mContext)
            mWebviewPop?.isVerticalScrollBarEnabled = false
            mWebviewPop?.isHorizontalScrollBarEnabled = false
            mWebviewPop?.webViewClient = UriWebViewClient()
            mWebviewPop?.settings?.javaScriptEnabled = true
            mWebviewPop?.settings?.savePassword = false
            mWebviewPop?.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            frame?.addView(mWebviewPop)
            val transport = resultMsg.obj as WebView.WebViewTransport
            transport.webView = mWebviewPop
            resultMsg.sendToTarget()

            return true
        }

        override fun onCloseWindow(window: WebView) {
            Log.d("onCloseWindow", "called")
        }

    }

}
