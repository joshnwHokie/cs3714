package com.example.t05

import android.media.AudioAttributes
import android.media.MediaPlayer

import android.media.AudioManager
import java.io.IOException





class MusicPlayer(val musicService: MusicService): MediaPlayer.OnCompletionListener {

    val MUSICPATH = arrayOf("http://people.cs.vt.edu/~shuoniu/mario.mp3", "http://people.cs.vt.edu/~shuoniu/tetris.mp3")

    val MUSICNAME = arrayOf("Super Mario", "Tetris")





}