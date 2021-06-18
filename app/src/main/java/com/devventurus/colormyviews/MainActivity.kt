package com.devventurus.colormyviews

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var boxOne : TextView
    lateinit var boxTwo : TextView
    lateinit var boxThree : TextView
    lateinit var boxFour : TextView
    lateinit var boxFive : TextView
    var boxOneColor = R.color.gray
    var boxTwoColor = R.color.gray
    var boxThreeColor = R.color.gray
    var boxFourColor = R.color.gray
    var boxFiveColor = R.color.gray
    val sharedPreferences : SharedPreferences
        get(){
            return this.getSharedPreferences("colors", Context.MODE_PRIVATE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boxOne = findViewById(R.id.boxOne)
        boxTwo = findViewById(R.id.boxTwo)
        boxThree = findViewById(R.id.boxThree)
        boxFour= findViewById(R.id.boxFour)
        boxFive= findViewById(R.id.boxFive)

        boxOneColor = sharedPreferences.getInt("boxOne", R.color.gray)
        boxTwoColor = sharedPreferences.getInt("boxTwo", R.color.gray)
        boxThreeColor = sharedPreferences.getInt("boxThree", R.color.gray)
        boxFourColor = sharedPreferences.getInt("boxFour", R.color.gray)
        boxFiveColor = sharedPreferences.getInt("boxFive", R.color.gray)
        boxOne.setBackgroundResource(boxOneColor)
        boxTwo.setBackgroundResource(boxTwoColor)
        boxThree.setBackgroundResource(boxThreeColor)
        boxFour.setBackgroundResource(boxFourColor)
        boxFive.setBackgroundResource(boxFiveColor)

        var chanceColor = R.color.gray
        var redButton = findViewById<Button>(R.id.redButton)
        var yellowButton = findViewById<Button>(R.id.yellowButton)
        var greenButton = findViewById<Button>(R.id.greenButton)



        redButton.setOnClickListener {
            chanceColor = R.color.red
        }

        yellowButton.setOnClickListener {
            chanceColor = R.color.yellow
        }

        greenButton.setOnClickListener {
            chanceColor = R.color.green
        }

        boxOne.setOnClickListener {
            boxOne.setBackgroundResource(chanceColor)
            boxOneColor = chanceColor
        }

        boxTwo.setOnClickListener {
            boxTwo.setBackgroundResource(chanceColor)
            boxTwoColor = chanceColor
        }

        boxThree.setOnClickListener {
            boxThree.setBackgroundResource(chanceColor)
            boxThreeColor = chanceColor
        }

        boxFour.setOnClickListener {
            boxFour.setBackgroundResource(chanceColor)
            boxFourColor = chanceColor
        }

        boxFive.setOnClickListener {
            boxFive.setBackgroundResource(chanceColor)
            boxFiveColor = chanceColor
        }
    }

    override fun onStop() {
        super.onStop()

        
        
        
        
        val editor = sharedPreferences.edit()

        editor.putInt("boxOne", boxOneColor)
        editor.putInt("boxTwo", boxTwoColor)
        editor.putInt("boxThree", boxThreeColor)
        editor.putInt("boxFour", boxFourColor)
        editor.putInt("boxFive", boxFiveColor)

        editor.commit()

    }
}
