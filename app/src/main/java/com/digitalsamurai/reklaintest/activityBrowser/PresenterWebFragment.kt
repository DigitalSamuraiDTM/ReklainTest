package com.digitalsamurai.reklaintest.activityBrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import moxy.MvpPresenter

class PresenterWebFragment : MvpPresenter<ViewStateWebActivity>() {

    private var bundle = Bundle()
    private var webClient = WebClient(this)

    @SuppressLint("SetJavaScriptEnabled")
    fun initState(web: WebView) {
        //если изначально настроек не было у вебвью, то инициализируем, иначе восстанавливаем
        if (bundle.isEmpty){
            // включаем поддержку JavaScript
            web.webViewClient = webClient

            web.settings.javaScriptEnabled = true
            // указываем страницу загрузки
            web.loadUrl("http://google.com/")
        } else{
            //так и не понял с чем это связано, но вебвьюклиент не сохраняется в бандле
            web.webViewClient = webClient
            web.restoreState(bundle)
        }
    }

    fun saveState(web: WebView) {
        web.saveState(bundle)
    }
}