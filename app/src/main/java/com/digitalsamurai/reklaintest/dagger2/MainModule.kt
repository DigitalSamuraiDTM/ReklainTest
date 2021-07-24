package com.digitalsamurai.reklaintest.dagger2

import android.widget.ImageButton
import androidx.annotation.NonNull
import com.digitalsamurai.reklaintest.activityBrowser.PresenterWebFragment
import com.digitalsamurai.reklaintest.activityGame.PresenterGameActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    @NonNull
    fun getGamePresenter(): PresenterGameActivity {
        return PresenterGameActivity()
    }

    @Provides
    @Singleton
    @NonNull
    fun getWebPresenter(): PresenterWebFragment{
        return PresenterWebFragment()
    }
}
