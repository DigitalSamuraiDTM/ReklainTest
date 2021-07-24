package com.digitalsamurai.reklaintest.dagger2

import com.digitalsamurai.reklaintest.activityBrowser.WebActivity
import com.digitalsamurai.reklaintest.activityGame.GameActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface AppDaggerComponent {
    fun injectGameActivity(activity : GameActivity)
    fun injectBrowserActivity(activity: WebActivity)
}