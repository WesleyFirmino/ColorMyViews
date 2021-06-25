package com.devventurus.colormyviews

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    var boxes = arrayOf( R.id.boxOne, R.id.boxTwo, R.id.boxThree, R.id.boxFour, R.id.boxFive )
    var colorLocate: Int = R.color.gray
    lateinit var contraintImage: ConstraintLayout
    lateinit var sharedPreferences : SharedPreferences
    val shareButton: FloatingActionButton? by lazy { findViewById(R.id.shareButton) }
    private val SOLICITAR_PERMISSAO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contraintImage = findViewById(R.id.imageContraint)

        shareButton?.setOnClickListener {
            takeScreenshot(contraintImage)?.let { it -> checkSharedPermission(it) }
        }

        sharedPreferences = getSharedPreferences("colors", MODE_PRIVATE)
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
    fun takeScreenshot(view: View) : Bitmap? {

        //Defina um bitmap com o mesmo tamanho da visualização
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        //Vincule uma tela a ele
        val canvas = Canvas(returnedBitmap)

        //Obtenha o background
        val bgDrawable = view.background

        if (bgDrawable != null) {
            bgDrawable.draw(canvas) //Obtenha o background em drawable, then draw it on the canvas
        } else {
            canvas.drawColor(Color.WHITE) //Se caso o não tiver o background em drawable a imagem será impressa em branco
        }

        view.draw(canvas)

        //chama a permissao
        return returnedBitmap
    }

    //Valida verificação
    fun checkSharedPermission(bitmap: Bitmap) {

        // Verifica  o estado da permissão de WRITE_EXTERNAL_STORAGE
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Se for diferente de PERMISSION_GRANTED, então vamos exibir a tela padrão
            ActivityCompat.requestPermissions( this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), SOLICITAR_PERMISSAO )
        } else {
            // Senão vamos compartilhar a imagem
            shareImage(bitmap)
        }
    }

    //Compartilha o bitmap
    fun shareImage(bitmap: Bitmap) {

        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        // Gravamos a imagem
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Obra de Arte", null)

        // criamos uam Uri com o endereço que a imagem foi salva
        val imageUri: Uri = Uri.parse(path)

        // Setmaos a Uri da imagem
        share.putExtra(Intent.EXTRA_STREAM, imageUri)

        // chama o compartilhamento
        startActivity(Intent.createChooser(share, "Selecione"))
    }


}