package com.devventurus.colormyviews

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var boxes = arrayOf( R.id.boxOne, R.id.boxTwo, R.id.boxThree, R.id.boxFour, R.id.boxFive )
    var colorLocate: Int = R.color.gray
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("colors", Context.MODE_PRIVATE)
        for (box in boxes) {
            findViewById<TextView>(box).setBackgroundResource(sharedPreferences.getInt("$box", colorLocate))
        }
    }

    fun onButtonClick(view: View) {
        colorLocate = when (view.id) {
            R.id.redButton -> R.color.red
            R.id.yellowButton -> R.color.yellow
            R.id.greenButton -> R.color.green
            else -> colorLocate
        }
    }

    fun onClickBox(view: View) {
        view.setBackgroundResource(colorLocate)
        var box = view.id
        with (sharedPreferences.edit()) {
            putInt("$box", colorLocate)
            commit()
        }
    }
}