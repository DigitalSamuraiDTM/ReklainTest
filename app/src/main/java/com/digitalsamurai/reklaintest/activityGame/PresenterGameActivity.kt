package com.digitalsamurai.reklaintest.activityGame

import moxy.MvpPresenter
import java.util.*
import kotlin.collections.ArrayList

class PresenterGameActivity : MvpPresenter<ViewStateGameActivity>() {
    private var turns = 0
    private var gameMatrix = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,0,0),
        intArrayOf(0,0,0)
    )

    //функция нажатия на игру
    fun ticTacToeClick(id: Pair<Int, Int>) {
        if (gameMatrix[id.first][id.second] == 0) {
            gameMatrix[id.first][id.second] = 1
            viewState.setCrestInThisButton(id)
            turns++
            checkWin(true)

        } else {
            viewState.toast(0)
        }
    }

    //функция расчета победы

    fun checkWin(wasUserTurn : Boolean){
        var a = intArrayOf(0,0,0)
        //columns
        for(i : Int in 0..gameMatrix.size-1){
            if(allElementsIsEqual(gameMatrix[i])){
                return
            }
        }
        //rows
        for(j : Int in 0..gameMatrix.size-1){
           for(i : Int in 0..gameMatrix.size-1){
               a[i] = (gameMatrix[i][j])
           }
            if (allElementsIsEqual(a)){
                return
            }
        }

        //left-top -> right-bottom
        for(i : Int in 0..gameMatrix.size-1){
            a[i] = gameMatrix[i][i]
        }
        if (allElementsIsEqual(a)){
            return
        }

        //right-top -> left->bottom
        for(i : Int in 0..gameMatrix.size-1){
            a[i] = gameMatrix[gameMatrix.size-1-i][i]
        }
        if (allElementsIsEqual(a)){
            return
        }


        //проверка на количество ходов (на 5-ый ход всегда ничья)
        if (turns==5) {
            turns = 0
            viewState.setPair()
            return
        }

        //если проверка была после нажатия, то сейчас ход компьютера, иначе ход юзера
        if (wasUserTurn){
            computeCirclePosition()
        }

    }

    //проверка на эквивалентность значений массива
    fun allElementsIsEqual(a : IntArray) : Boolean {
        if ((a[0]==a[1]) && (a[1]==a[2]) && (a[2]==1)){
            viewState.userWin()
            return true
        } else if ((a[0]==a[1]) && (a[1]==a[2]) && (a[2]==2)){
            viewState.computerWin()
            return true
        }
        return false
    }

    //мозг компьютера. Не стал делать расчеты шагов, так как там всё шаблонно, оставлю "случайность"
    fun computeCirclePosition(){
        val random = Random()
        while (true){
            val row = random.nextInt(3)
            val col = random.nextInt(3)
            if (gameMatrix[row][col] ==0){
                viewState.setCircleInThisButton(Pair(row,col))
                gameMatrix[row][col] = 2
                checkWin(false)
                return
            }
        }
    }

    //функция рестарта игры
    fun restartGame() {
        gameMatrix = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(0,0,0),
            intArrayOf(0,0,0)
        )
        turns = 0

        //вызывая эту функцию у нас очищается viewState
        viewState.clearGameMatrix()
    }

}