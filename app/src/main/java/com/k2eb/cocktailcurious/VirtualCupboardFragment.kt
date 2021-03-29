package com.k2eb.cocktailcurious

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class VirtualCupboardFragment : Fragment() {

    var isEmpty = true
    var cupboardList = mutableListOf<Ingredient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //cupboardList = mutableListOf<Ingredient>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCupboardView(view)

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

    // TODO method that sets the view in the virtual cupboard

   // fun setCupboardView (view : View) {
    // }

    /**
     * sets the buttons, textview and recyclerviews available on the view depending on the
     * size of the cupboardList
     */
    fun setCupboardView(view: View) {
        val btnBigAdd = view.findViewById<Button>(R.id.btn_big_add)
        val txvPrompt = view.findViewById<TextView>(R.id.txv_prompt)
        val btnSmallAdd = view.findViewById<Button>(R.id.btn_small_add)
        val btnLetsGo = view.findViewById<Button>(R.id.btn_lets_go)
        val btnClearAll = view.findViewById<Button>(R.id.btn_clear_all)
        val rvIngredients = view.findViewById<RecyclerView>(R.id.rv_ingredients)
        if (cupboardList.size == 0) {
            //findViewById(R.id.btn_big_add) setVisbility (View.VISIBLE)
            btnBigAdd.isClickable = true
            btnBigAdd.visibility = View.VISIBLE
            //findViewById(R.id.txv_prompt) setVisbility (View.VISIBLE)
            txvPrompt.visibility = View.VISIBLE
            //findViewById(R.id.btn_small_add) setVisbility (View.INVISIBLE)
            btnSmallAdd.isClickable = false
            btnSmallAdd.visibility = View.INVISIBLE
            //findViewById(R.id.btn_lets_go) setVisbility (View.INVISIBLE)
            btnLetsGo.isClickable = false
            btnLetsGo.visibility = View.INVISIBLE
            //findViewById(R.id.btn_clear_all) setVisbility (View.INVISIBLE)
            btnClearAll.isClickable = false
            btnClearAll.visibility = View.INVISIBLE
            //findViewById(R.id.rv_ingredients) setVisbility (View.INVISIBLE)
            rvIngredients.isClickable = false
            rvIngredients.visibility = View.INVISIBLE
        } else {
            //view.findViewById<Button>(R.id.btn_big_add) setVisbility (View.INVISIBLE)
            btnBigAdd.isClickable = false
            btnBigAdd.visibility = View.INVISIBLE
            //view.findViewById<TextView>(R.id.txv_prompt) setVisbility (View.INVISIBLE)
            txvPrompt.visibility = View.INVISIBLE
            //view.findViewById<Button>(R.id.btn_small_add) setVisbility (View.VISIBLE)
            btnSmallAdd.isClickable = true
            btnSmallAdd.visibility = View.VISIBLE
            //view.findViewById<Button>(R.id.btn_lets_go) setVisbility (View.VISIBLE)
            btnLetsGo.isClickable = true
            btnLetsGo.visibility = View.VISIBLE
            //view.findViewById<Button>(R.id.btn_clear_all) setVisbility (View.VISIBLE)
            btnClearAll.isClickable = true
            btnClearAll.visibility = View.VISIBLE
            //view.findViewById<Button>(R.id.rv_ingredients) setVisbility (View.VISIBLE)
            rvIngredients.isClickable = true
            rvIngredients.visibility = View.VISIBLE
        }
        searchPopup.showAtLocation(view, Gravity.CENTER, 0, 0)
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

    /**
     * if the cupboardList is empty then the user is informed that it's already empty
     * else, the list is cleared
     */
    fun onClickClearAll() {
        if (cupboardList.isEmpty()) {
            Toast.makeText(activity,"Your cupboard is already empty", Toast.LENGTH_SHORT).show()
        } else {
            cupboardList.clear()
        }
    }

}