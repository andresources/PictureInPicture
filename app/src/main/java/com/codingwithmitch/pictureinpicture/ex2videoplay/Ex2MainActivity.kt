package com.codingwithmitch.pictureinpicture.ex2videoplay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.codingwithmitch.pictureinpicture.R

class Ex2MainActivity : AppCompatActivity() {
    private lateinit var mVOne: Button
    private lateinit var mVTwo: Button
    private lateinit var mVThree: Button
    companion object {
        private const val videoOneURL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        private const val videoTwoURL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        private const val videoThreeURL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2_main)
        mVOne = findViewById(R.id.videoOneBtn)
        mVTwo = findViewById(R.id.videoTwoBtn)
        mVThree = findViewById(R.id.videoThreeBtn)
//        Clicks
        mVOne.setOnClickListener {
            playVideo(videoOneURL)
        }
        mVTwo.setOnClickListener {
            playVideo(videoTwoURL)
        }
        mVThree.setOnClickListener {
            playVideo(videoThreeURL)
        }

    }

    private fun playVideo(url: String) {
        val intent = Intent().apply {
            setClass(applicationContext, PIPActivity::class.java)
            putExtra("videoURL", url)
        }
        startActivity(intent)
    }

}