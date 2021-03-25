package com.k2eb.cocktailcurious

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class VirtualCupboardFragment : Fragment() {

    var isEmpty = true
    var cupboardList = mutableListOf<Ingredient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cupboardList = mutableListOf<Ingredient>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val letsGoButton = view.findViewById<Button>(R.id.btn_lets_go)
        val clearButton = view.findViewById<Button>(R.id.btn_clear_all)

        letsGoButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, YourMenuFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar(getString(R.string.your_menu_title))
            transaction?.commit()
        }

        clearButton.setOnClickListener {
            if (cupboardList.isEmpty()) {
                Toast.makeText(activity, "Your cupboard is empty.", Toast.LENGTH_LONG).show()
            } else {
                cupboardList.clear()
            }
        }
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