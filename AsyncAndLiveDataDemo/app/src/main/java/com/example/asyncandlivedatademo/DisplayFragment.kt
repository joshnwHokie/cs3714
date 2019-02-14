package com.example.asyncandlivedatademo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


class DisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_display, container, false)
//        var temp = activity

//        if(temp!=null){
//
//          val model =  ViewModelProviders.of(temp).get(MyViewModel::class.java)
//        }
        val model = activity?.let { ViewModelProviders.of(it).get(MyViewModel::class.java)}


        model?.getElapsedTime()?.observe(this, Observer<Long>{ time ->

            v?.findViewById<TextView>(R.id.timer)?.text = "$time seconds elapsed"

        })


        model?.getLapTime()?.observe(this, Observer<String>{ laps ->

             v?.findViewById<TextView>(R.id.laps)?.text = laps

        })


        return v
    }


}
