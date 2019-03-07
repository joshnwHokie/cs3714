package com.example.pp03


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders



/**
 * A simple [Fragment] subclass.
 *
 */
class MiddleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_middle, container, false)




        val model = activity?.run{ ViewModelProviders.of(this).get(Model::class.java)}?: throw Exception("Invalid Activity")

        model?.getValues().observe(this, Observer<List<Int>> {values ->

            Log.d("pp03", "values"+values.toString())
            v.findViewById<TextView>(R.id.middle).text = values[1].toString()

        })


        return v
    }


}
