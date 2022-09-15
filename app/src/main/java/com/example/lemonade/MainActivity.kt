/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView = findViewById(R.id.image_lemon_state)

    /**
     * 自分で付け足した変数です。
     */
    //何杯作ったか数える変数
    private var lemonadeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===

        // TODO:setViewElements()を呼び出し、初期画面を設定
        setViewElements()

        lemonImage.setOnClickListener {
            // TODO:画像をクリックされたら、画像を設定するメソッドを呼び出す
            clickLemonImage()
        }

        lemonImage.setOnLongClickListener {
            // TODO: 絞った回数を表示するメソッド呼び出し、 lemonadeStateがSQUEEZEだった場合、処理を行う
            showSnackbar()
        }
        //CHANGEボタン
        val changeButton = findViewById<Button>(R.id.change_button)
        /* RESETボタン */
        val resetButton = findViewById<Button>(R.id.reset_button)

        // CHANGEボタンが押された際の処理
        changeButton.setOnClickListener {
            // TODO:lemonadeStateをSELECTに戻し、最初のtree画像に戻す
            lemonadeState = SELECT
            setViewElements()
        }

        //RESETボタンが押された際の処理
        resetButton.setOnClickListener {
            // TODO:lemonadeStateをSELECTに戻し、最初のtree画像に戻す
            lemonadeState = SELECT

            //作ったレモネードのカウントを０にリセット
            lemonadeCount = 0
            setViewElements()
            setMsg()
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    /**
     * 画像がクリックされたらlemonadeStateを変更するメソッド
     */
    private fun clickLemonImage() {
        when (lemonadeState) {
            SELECT -> {
                // lemonadeStateがSELECTの場合、SQUEEZEに変更し、絞る回数をpick()から取得
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            SQUEEZE -> {
                // lemonadeStateがSQUEEZEの時、絞った回数を1クリックごとにプラスし、
                // 絞る回数をマイナス1する。
                // 絞る回数のlemonSizeが０になった時、絞り終わったということでlemonadeStateをDRINKに変更
                squeezeCount++
                lemonSize--
                if (lemonSize == 0) {
                    lemonadeState = DRINK
                }
            }
            DRINK -> {
                lemonadeState = RESTART
                lemonSize = -1
            }
            RESTART -> {
                // lemonadeStateがRestart時にクリックされたら、初期状態のSelectに戻る。
                lemonadeState = SELECT

                // lemonadeを作ったので、メッセージを表示するメソッドを呼び出す。
                lemonadeCount++
                setMsg()
            }
        }
        // lemonadeStateが更新されるたびに、画像も変化させる必要があるので、
        // 画像がクリックされる度に、画像を表示するメソッドを毎回呼び出す。
        setViewElements()
    }

    /**
     * lemonadeStateに応じて、表示する画像を変更するメソッド
     */
    private fun setViewElements() {
        val lemonText: TextView = findViewById(R.id.text_action)
        when (lemonadeState) {
            SELECT -> {
                lemonText.setText(R.string.lemon_select)
                lemonImage.setImageResource(R.drawable.lemon_tree)
            }
            SQUEEZE -> {
                lemonText.setText(R.string.lemon_squeeze)
                lemonImage.setImageResource(R.drawable.lemon_squeeze)
            }
            DRINK -> {
                lemonText.setText(R.string.lemon_drink)
                lemonImage.setImageResource(R.drawable.lemon_drink)
            }
            RESTART -> {
                lemonText.setText(R.string.lemon_empty_glass)
                lemonImage.setImageResource(R.drawable.lemon_restart)
            }
        }
    }

    /**
     * lemonadeCountに応じて、作ったレモネードの杯数をメッセージで表示するメソッド
     */
    private fun setMsg() {
        val lemonadeMsg: TextView = findViewById(R.id.lemonadeMsg)
        val msg: String = if (lemonadeCount == 0) {
            "Make Lemonade!!"
        } else if (lemonadeCount > 1) {
            "You have made $lemonadeCount Lemonades!!"
        } else {
            "You have made $lemonadeCount Lemonade!!"
        }
        lemonadeMsg.text = msg
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * 画像を長押しすることで、何回絞ったか表示するメソッド
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

/**
 * ランダムに生成された数字によって、レモンを絞る回数を決定するメソッド
 */
class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}