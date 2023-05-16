package com.codingwithmitch.pictureinpicture

import android.app.PictureInPictureParams
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.Display
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun funMyClick(view: View) {
        val pip_Builder = PictureInPictureParams.Builder()
        pip_Builder.setAspectRatio(Rational(9,16)).build()
        enterPictureInPictureMode(pip_Builder.build())
    }
}