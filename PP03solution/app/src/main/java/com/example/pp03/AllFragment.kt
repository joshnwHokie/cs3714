package com.example.pp03


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AllFragment : Fragment() {

    private lateinit var left: TextView
    private lateinit var middle: TextView
    private lateinit var right: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val v = inflater.inflate(R.layout.fragment_all, container, false)


        left = v.findViewById(R.id.left)
        middle = v.findViewById(R.id.middle)
        right = v.findViewById(R.id.right)


        left.setOnClickListener{
                it.findNavController().navigate(R.id.action_allFragment_to_leftFragment)

        }


        middle.setOnClickListener{
            it.findNavController().navigate(R.id.action_allFragment_to_middleFragment)

        }

        right.setOnClickListener{
            it.findNavController().navigate(R.id.action_allFragment_to_rightFragment)

        }



        val model = activity?.let { ViewModelProviders.of(it).get(Model::class.java) }

        (v.findViewById(R.id.start) as Button).setOnClickListener{
            model?.slotMachineDraw()
        }


        (v.findViewById(R.id.stop) as Button).setOnClickListener{
            model?.stop()
        }


        model?.getValues()?.observe(this, Observer<List<Int>>{ list ->

            v?.findViewById<TextView>(R.id.left)?.text = list[0].toString()
            v?.findViewById<TextView>(R.id.middle)?.text = list[1].toString()
            v?.findViewById<TextView>(R.id.right)?.text = list[2].toString()

        })


        return v
    }


}
