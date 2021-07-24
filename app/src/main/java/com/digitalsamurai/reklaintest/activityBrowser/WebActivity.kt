package com.digitalsamurai.reklaintest.activityBrowser

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.ProgressBar
import com.digitalsamurai.reklaintest.MainApplication
import com.digitalsamurai.reklaintest.R
import com.digitalsamurai.reklaintest.activityGame.PresenterGameActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class WebActivity : MvpAppCompatActivity(), ViewStateWebActivity {


    @Inject
    lateinit var presenterProvider : Provider<PresenterWebFragment>

    val presenter by moxyPresenter {presenterProvider.get()};

    private lateinit var web : WebView
    private lateinit var progressLoading : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.getDagger().injectBrowserActivity(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        setTitle("Web")

        if(intent?.extras?.getBoolean("FromPush",false) == true){
            val editor = this.getSharedPreferences("ReklainTest", MODE_PRIVATE).edit()
            editor.putBoolean("FromPush", true)
            editor.apply()
        }

        progressLoading = findViewById(R.id.fr_browser_progress_loading)
        web  = findViewById(R.id.fr_browser_webview)
    }

    override fun onBackPressed() {
        if (web.canGoBack()){
            web.goBack()
        } else{
            super.onBackPressed()
        }
    }

    override fun onPause() {

        presenter.saveState(web)
        super.onPause()
    }

    override fun onResume() {

        presenter.initState(web)
        super.onResume()
    }

    override fun showLoading() {
        progressLoading.visibility = View.VISIBLE
        web.visibility = View.INVISIBLE
    }

    override fun showData() {
        progressLoading.visibility = View.INVISIBLE
        web.visibility = View.VISIBLE
    }
}