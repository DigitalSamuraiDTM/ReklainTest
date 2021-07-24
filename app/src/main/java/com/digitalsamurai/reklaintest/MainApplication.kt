package com.digitalsamurai.reklaintest

import android.app.Application
import com.digitalsamurai.reklaintest.dagger2.AppDaggerComponent
import com.digitalsamurai.reklaintest.dagger2.DaggerAppDaggerComponent
import com.digitalsamurai.reklaintest.dagger2.MainModule

class MainApplication : Application() {
    override fun onCreate() {

        daggerComponent = buildDagger()

        super.onCreate()
    }

    fun buildDagger() : AppDaggerComponent{
        return DaggerAppDaggerComponent.builder().mainModule(MainModule()).build()
    }

    companion object{
        private lateinit var daggerComponent: AppDaggerComponent
        fun getDagger() : AppDaggerComponent{
            return daggerComponent
        }
    }
}