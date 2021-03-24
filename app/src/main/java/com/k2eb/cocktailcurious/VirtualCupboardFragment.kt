package com.k2eb.cocktailcurious

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class VirtualCupboardFragment : Fragment() {

    var isEmpty = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cupboardList = mutableListOf<Ingredient>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_virtual_cupboard, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            VirtualCupboardFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    // TODO method to add/remove ingredients to cupboard database
    /**
     * Add ingredient
     * include check for empty cupboard
     */

    /**
     * remove ingredient
     * include check for empty cupboard
     */

}