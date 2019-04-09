package com.example.pp06_starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var currentDegree = 0.0f
    lateinit var left: Button
    lateinit var right: Button
    //suggested picture: https://clipart.wpblink.com/wallpaper-1606027
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        left = findViewById(R.id.left)
        right = findViewById(R.id.right)
        image = findViewById(R.id.plane)

        left.setOnClickListener(this)
        right.setOnClickListener(this)


    }


    override fun onClick(v: View?) {


        if(v!=null && v == left){
            val rotateAnimation = RotateAnimation(
                currentDegree,
                currentDegree-45f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 1000
            rotateAnimation.fillAfter = true

            image.startAnimation(rotateAnimation)
            currentDegree -= 45f
        }
        if(v!=null && v == right){
            val rotateAnimation = RotateAnimation(
                currentDegree,
                currentDegree+45f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            rotateAnimation.duration = 1000
            rotateAnimation.fillAfter = true

            image.startAnimation(rotateAnimation)
            currentDegree += 45f

        }
    }
}
