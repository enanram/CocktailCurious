package com.k2eb.cocktailcurious

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class VirtualCupboardFragment : Fragment() {

    lateinit var ingredientRecycler: RecyclerView
    lateinit var cupboardRecycler: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val flater = inflater.inflate(R.layout.fragment_virtual_cupboard, container, false)
        cupboardRecycler = flater.findViewById(R.id.rv_ingredients)
        cupboardRecycler.layoutManager = LinearLayoutManager(cupboardRecycler.context)
        cupboardRecycler.adapter = CupboardRecyclerAdapter(cupboardList)

        val letsGoButton = flater.findViewById<Button>(R.id.btn_lets_go)
        val clearButton = flater.findViewById<Button>(R.id.btn_clear_all)
        val bigAddButton = flater.findViewById<Button>(R.id.btn_big_add)
        val smallAddButton = flater.findViewById<Button>(R.id.btn_small_add)

        setCupboardView(flater)

        letsGoButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, YourMenuFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar(getString(R.string.your_menu_title))
            transaction?.commit()
        }


        bigAddButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IngredientSearchFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar("Ingredient Search")
            transaction?.commit()
        }
        smallAddButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IngredientSearchFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar("Ingredient Search")
            transaction?.commit()
        }


        bigAddButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IngredientSearchFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar("Ingredient Search")
            transaction?.commit()
        }
        smallAddButton.setOnClickListener {
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, IngredientSearchFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar("Ingredient Search")
            transaction?.commit()
        }

        clearButton.setOnClickListener {
            if (cupboardList.isEmpty()) {
                Toast.makeText(activity, "Your cupboard is empty.", Toast.LENGTH_LONG).show()
            } else {
                cupboardList.clear()
            }
        }

        return flater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        var isEmpty = true
        var cupboardList = mutableListOf<Ingredient>()

        @JvmStatic
        fun newInstance() =
            VirtualCupboardFragment().apply {
                arguments = Bundle().apply {
                }
            }

        // Add ingredient. Includes check for empty cupboard
        fun addIngredient(ingredient: Ingredient) {
            cupboardList.add(ingredient)
            if (cupboardList.size > 0) {
                isEmpty = false
            }
        }

        // Remove ingredient. Includes check for empty cupboard
        fun removeIngredient(ingredient: Ingredient) {
            cupboardList.remove(ingredient)
            if (cupboardList.size == 0) {
                isEmpty = true
            }
        }
    }

    /**
     * sets the buttons, textview and recyclerviews available on the view depending on the
     * size of the cupboardList
     */
    private fun setCupboardView(view: View) {
        val btnBigAdd = view.findViewById<Button>(R.id.btn_big_add)
        val txvPrompt = view.findViewById<TextView>(R.id.txv_prompt)
        val btnSmallAdd = view.findViewById<Button>(R.id.btn_small_add)
        val btnLetsGo = view.findViewById<Button>(R.id.btn_lets_go)
        val btnClearAll = view.findViewById<Button>(R.id.btn_clear_all)
        val rvIngredients = view.findViewById<RecyclerView>(R.id.rv_ingredients)
        if (cupboardList.size == 0) {
            btnBigAdd.isClickable = true
            btnBigAdd.visibility = View.VISIBLE
            txvPrompt.visibility = View.VISIBLE
            btnSmallAdd.isClickable = false
            btnSmallAdd.visibility = View.INVISIBLE
            btnLetsGo.isClickable = false
            btnLetsGo.visibility = View.INVISIBLE
            btnClearAll.isClickable = false
            btnClearAll.visibility = View.INVISIBLE
            rvIngredients.isClickable = false
            rvIngredients.visibility = View.INVISIBLE
        } else {
            btnBigAdd.isClickable = false
            btnBigAdd.visibility = View.INVISIBLE
            txvPrompt.visibility = View.INVISIBLE
            btnSmallAdd.isClickable = true
            btnSmallAdd.visibility = View.VISIBLE
            btnLetsGo.isClickable = true
            btnLetsGo.visibility = View.VISIBLE
            btnClearAll.isClickable = true
            btnClearAll.visibility = View.VISIBLE
            rvIngredients.isClickable = true
            rvIngredients.visibility = View.VISIBLE
        }
    }

    /*private fun popupIngredientSearch() {

        // Inflate the display.
        val flater: View = LayoutInflater.from(activity).inflate(R.layout.fragment_search_popup, null,false)
        ingredientRecycler = flater.findViewById(R.id.ingredient_results)
        val searchPopup = PopupWindow(ingredientRecycler)
        // Apply the adapter to the RecyclerView
        ingredientRecycler.adapter = IngredientRecyclerAdapter(MainActivity.makeIngredientsList())
        // Allow the exit button to close the popup
        val exitButton = flater.findViewById<Button>(R.id.btn_x)
        exitButton.setOnClickListener {
            searchPopup.dismiss()
        }
        searchPopup.showAtLocation(view, Gravity.CENTER, 0, 0)
    }*/
}