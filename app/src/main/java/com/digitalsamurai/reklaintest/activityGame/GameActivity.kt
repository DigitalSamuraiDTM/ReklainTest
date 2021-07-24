package com.digitalsamurai.reklaintest.activityGame

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.digitalsamurai.reklaintest.MainApplication
import com.digitalsamurai.reklaintest.R
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class GameActivity : MvpAppCompatActivity(), ViewStateGameActivity, View.OnClickListener {

    @Inject
    lateinit var presenterProvider : Provider<PresenterGameActivity>

    val presenter by moxyPresenter {presenterProvider.get()};

    //массив хранит указатель ресурса и расположение на матрице
    private lateinit var btnArray : HashMap<Int, Pair<Int, Int>>

    private lateinit var gameLay : GridLayout
    private lateinit var viewGame : TextView
    private lateinit var btnRestart : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        MainApplication.getDagger().injectGameActivity(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)

        setTitle("Game")
        //много кнопок

        btnArray = HashMap()
        btnArray.put(R.id.fr_game_imbtn_00, Pair(0,0))
            btnArray.put(R.id.fr_game_imbtn_01,Pair(0,1))
            btnArray.put(R.id.fr_game_imbtn_02,Pair(0,2))
            btnArray.put(R.id.fr_game_imbtn_10,Pair(1,0))
            btnArray.put(R.id.fr_game_imbtn_11,Pair(1,1))
            btnArray.put(R.id.fr_game_imbtn_12,Pair(1,2))
            btnArray.put(R.id.fr_game_imbtn_20,Pair(2,0))
            btnArray.put(R.id.fr_game_imbtn_21,Pair(2,1))
            btnArray.put(R.id.fr_game_imbtn_22,Pair(2,2))


        gameLay = findViewById(R.id.fr_game_gameLayout)
        viewGame = findViewById(R.id.fr_game_textView_gameStatus)
        btnRestart = findViewById(R.id.fr_game_btn_restart)
        btnRestart.setOnClickListener {
            btnRestart.visibility = View.INVISIBLE
            viewGame.visibility = View.INVISIBLE
            presenter.restartGame()
        }

    }

    override fun onClick(p0: View?) {
        presenter.ticTacToeClick(btnArray.get(p0?.id)!!)
    }


    //функция ставит крестик на кнопку. Так как мы не имеем ссылки на все кнопки, то ищем с помощью эквиваленции хэшей Pair из Hashmap
    //к сожалению лучше способа не придумал :с
    override fun setCrestInThisButton(id: Pair<Int, Int>) {
        val c = gameLay.childCount
        for (i : Int in 0 until c) {

            with(gameLay.getChildAt(i).id) {
                if ((btnArray.get(this)).hashCode().equals(id.hashCode())) {

                    with((findViewById(this) as ImageButton)){
                        this.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this@GameActivity,
                                R.drawable.ic_crest_black))
                        this.isClickable = false

                    }
                }
            }
        }

    }

    //функция ставит нолик на кнопку. Так как мы не имеем ссылки на все кнопки, то ищем с помощью эквиваленции хэшей Pair из Hashmap

    override fun setCircleInThisButton(id: Pair<Int, Int>) {
        val c = gameLay.childCount
        for (i : Int in 0 until c) {

            with(gameLay.getChildAt(i).id) {
                if ((btnArray.get(this)).hashCode().equals(id.hashCode())) {

                        with((findViewById(this) as ImageButton)){
                            this.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this@GameActivity,
                                R.drawable.ic_circle_black))
                            this.isClickable = false
                        }
                }
            }
        }
    }

    override fun toast(i: Int) {
        when(i){
            1->{
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun userWin() {
        viewGame.visibility = View.VISIBLE
        viewGame.setText("You Win")
        btnRestart.visibility = View.VISIBLE
    }

    override fun computerWin() {
        viewGame.visibility = View.VISIBLE
        viewGame.setText("You Lose")
        btnRestart.visibility = View.VISIBLE
    }

    override fun setPair() {
        viewGame.visibility = View.VISIBLE
        viewGame.setText("Pair")
        btnRestart.visibility = View.VISIBLE
    }

    override fun clearGameMatrix() {
        val c = gameLay.childCount
        for (i: Int in 0 until c) {
            with(gameLay.getChildAt(i) as ImageButton) {
                this.isClickable = true
                this.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this@GameActivity,
                        R.drawable.ic_clear
                    )
                )
            }
        }
    }
}