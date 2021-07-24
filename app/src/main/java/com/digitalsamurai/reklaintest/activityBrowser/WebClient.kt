package com.digitalsamurai.reklaintest.activityBrowser

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebClient(val presenter : PresenterWebFragment) : WebViewClient() {

    //веб клиент для обработки дальнейшей навигации в вебвью
    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }

    // Для старых устройств
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    //для соблюдения канонов можно реализовать инверсию зависимостей, но не вижу в этом смысла

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        presenter.viewState.showLoading()
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        presenter.viewState.showData()
        super.onPageFinished(view, url)
    }
}