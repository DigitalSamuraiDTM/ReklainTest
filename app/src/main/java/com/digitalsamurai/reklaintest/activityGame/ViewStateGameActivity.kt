package com.digitalsamurai.reklaintest.activityGame

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.SingleState
import moxy.viewstate.strategy.alias.Skip
import moxy.viewstate.strategy.alias.AddToEndSingle

interface ViewStateGameActivity : MvpView {

    @AddToEnd
    fun setCrestInThisButton(id: Pair<Int, Int>)
    @Skip
    fun toast(i: Int)
    @AddToEnd
    fun setCircleInThisButton(id: Pair<Int, Int>)
    @AddToEndSingle
    fun userWin()
    @AddToEndSingle
    fun computerWin()

    @AddToEndSingle
    fun setPair()

    @SingleState
    fun clearGameMatrix()

}