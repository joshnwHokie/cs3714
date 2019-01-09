package com.example.andrey.shippingratecalculator

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var usd_seek_bar: SeekBar = findViewById(R.id.usd_seek_bar)
        var lbs_seek_bar: SeekBar = findViewById(R.id.lbs_seek_bar)

        var usd_total: TextView = findViewById(R.id.usd_total)
        var lbs_total: TextView = findViewById(R.id.lbs_total)


        usd_seek_bar.setMin(1)

        usd_seek_bar.setMax(125)

        lbs_seek_bar.setMin(1)

        lbs_seek_bar.setMax(100)


        usd_seek_bar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            var usd_sync=0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //progress changed
                usd_total.setText("USD:"+progress)
                usd_sync=progress



                findViewById<TextView>(R.id.total).setText("Total cost:"+progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
               //when change starts
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if(usd_sync.toFloat()<10/1.75)
                    lbs_seek_bar.setProgress(round(usd_sync/1.75f).toInt())
                else
                    lbs_seek_bar.setProgress(round(usd_sync/1.25f).toInt())
            }
        })

        lbs_seek_bar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            var lbs_sync=0

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //progress changed

                lbs_total.setText("lbs:"+progress)

                lbs_sync=progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

                //when change starts
//                if(lbs_sync<10)
//                    usd_seek_bar.setProgress(round(lbs_sync/1.75f).toInt())
//                else
//                    usd_seek_bar.setProgress(round(lbs_sync/1.25f).toInt())
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if(lbs_sync<10)
                    usd_seek_bar.setProgress(round(lbs_sync*1.75f).toInt())
                else
                    usd_seek_bar.setProgress(round(lbs_sync*1.25f).toInt())
            }
        })

    }



}
