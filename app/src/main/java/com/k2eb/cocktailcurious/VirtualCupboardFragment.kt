package com.k2eb.cocktailcurious

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class VirtualCupboardFragment : Fragment() {

    var isEmpty = true
    var cupboardList = mutableListOf<Ingredient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //cupboardList = mutableListOf<Ingredient>()
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
     * Include check for empty cupboard
     */

    fun addIngredient(ingredient: Ingredient) {
        cupboardList.add(ingredient)
        if (cupboardList.size > 0) {
            isEmpty = false
        }
    }

    /**
     * Remove ingredient
     * Include check for empty cupboard
     */

    fun removeIngredient(ingredient: Ingredient) {
        cupboardList.remove(ingredient)
        if (cupboardList.size == 0) {
            isEmpty = true
        }
    }
}