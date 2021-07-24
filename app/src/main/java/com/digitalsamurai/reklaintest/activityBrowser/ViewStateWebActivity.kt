package com.digitalsamurai.reklaintest.activityBrowser

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface ViewStateWebActivity : MvpView {

    @SingleState
    fun showLoading()

    @SingleState
    fun showData()
}