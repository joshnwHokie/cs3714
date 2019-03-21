package com.example.t05

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.os.IBinder
import android.view.View


//Based on Shuo Niu's 5.2 Service and BroadcastReceiver Hands-on Tutorial



class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {

    }

    val INITIALIZE_STATUS = "intialization status"
    val MUSIC_PLAYING = "music playing"

    var play: Button? = null
    var music: TextView? = null







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun updateName(musicName: String) {
        music?.text = "You are listening to " + musicName
    }
}
