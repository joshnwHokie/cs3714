package com.example.asyncandlivedatademo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders


class ControlFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_control, container, false)

        val model = activity?.let { ViewModelProviders.of(it).get(MyViewModel::class.java) }

        (v.findViewById(R.id.startstop) as Button).setOnClickListener{
             model?.startstop()
        }

        (v.findViewById(R.id.lap) as Button).setOnClickListener{
            model?.slowLapCapture()
        }

        (v.findViewById(R.id.clear) as Button).setOnClickListener{
            model?.slowClear()
        }

        return v
    }


}
