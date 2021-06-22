package com.devventurus.colormyviews

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var boxes = arrayOf( R.id.boxOne, R.id.boxTwo, R.id.boxThree, R.id.boxFour, R.id.boxFive )
    var colorLocate: Int = R.color.gray
    lateinit var sharedPreferences : SharedPreferences
    val shareButton: FloatingActionButton? by lazy { findViewById(R.id.shareButton) }

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

    // Return a bitmap
    fun takeScreenshot(view: View, height: Int, width: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return bitmap
    }
}