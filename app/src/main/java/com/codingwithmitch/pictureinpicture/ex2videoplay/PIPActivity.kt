package com.codingwithmitch.pictureinpicture.ex2videoplay
import android.app.PictureInPictureParams
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import com.codingwithmitch.pictureinpicture.R

class PIPActivity : AppCompatActivity() {
    private lateinit var mPipBtn: ImageView
    private lateinit var tv:LinearLayout
    //private lateinit var mVideoView: VideoView
    private val TAG = "PIP_TAG"

    //    Extra
    private var videoUri: Uri? = null
    private var pictureInPictureParamsBuilder: PictureInPictureParams.Builder? = null
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_i_p)
        mPipBtn = findViewById(R.id.pipBtn)
        tv = findViewById(R.id.ll)
       // mVideoView = findViewById(R.id.videoView)

        actionBar = supportActionBar

//        init the picture in picture view
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pictureInPictureParamsBuilder = PictureInPictureParams.Builder()
        }
        // click
        mPipBtn.setOnClickListener {
            pictureInPictureMode()
        }

    }

    override fun onBackPressed() {
        pictureInPictureMode()
    }


    private fun pictureInPictureMode() {
        Log.d(TAG, "pictureInPictureMode: Try to enter PIP mode")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "pictureInPictureMode: Device Support PIP Mode")
            //val aspectRatio = Rational(tv.width, tv.height)
            val aspectRatio = Rational(1,1)
            pictureInPictureParamsBuilder!!.setAspectRatio(aspectRatio).build()
            enterPictureInPictureMode(pictureInPictureParamsBuilder!!.build())
        } else {
            Log.d(TAG, "pictureInPictureMode: Doesn't support PIP")
            Toast.makeText(
                applicationContext,
                "Your device doesn't supports PIP",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "onUserLeaveHint: Was not in PIP")
            pictureInPictureMode()
        } else {
            Log.d(TAG, "onUserLeaveHint: Already in PIP Mode")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            Log.d(TAG, "onPictureInPictureModeChanged: Entered PIP")
            mPipBtn.visibility = View.VISIBLE
            actionBar!!.hide()
        } else {
            Log.d(TAG, "onPictureInPictureModeChanged: Exited PIP")
            mPipBtn.visibility = View.GONE
            actionBar!!.show()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
       /* if (mVideoView.isPlaying) {
            mVideoView.stopPlayback()
        }*/
    }
}