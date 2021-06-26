package com.devventurus.colormyviews

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View

fun View.takeScreeshot() : Bitmap {

    val returnedBitmap = Bitmap.createBitmap(this.width, 600, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    val backgroundDrawable = this.background

    if (backgroundDrawable != null) {
        backgroundDrawable.draw(canvas)
    } else {
        canvas.drawColor(Color.WHITE)
    }

    this.draw(canvas)
    return returnedBitmap
}