package com.example.pp03


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LeftFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val v = inflater.inflate(R.layout.fragment_left, container, false)




        val model = activity?.run{ ViewModelProviders.of(this).get(Model::class.java)}?: throw Exception("Invalid Activity")

        model?.getValues().observe(this, Observer<List<Int>> {values ->

                    v.findViewById<TextView>(R.id.left).text = values[0].toString()

        })


        return v
    }


}
