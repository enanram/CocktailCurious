package com.k2eb.cocktailcurious

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IngredientSearchFragment : Fragment() {

    lateinit var ingredientRecycler: RecyclerView
    lateinit var adapt: IngredientRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val flater: View = LayoutInflater.from(activity).inflate(R.layout.fragment_search_popup, container,false)
        ingredientRecycler = flater.findViewById(R.id.ingredient_results)
        ingredientRecycler.layoutManager = LinearLayoutManager(ingredientRecycler.context)
        adapt = IngredientRecyclerAdapter(Ingredient.ingredientList)
        ingredientRecycler.adapter = adapt

        val exitBtn = flater.findViewById<Button>(R.id.btn_x)
        exitBtn.setOnClickListener{
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, VirtualCupboardFragment())
            (activity as MainActivity).setTitleBar("Virtual Cupboard")
            transaction?.commit()
        }

        val searchBar = flater.findViewById<SearchView>(R.id.ingredient_search)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(input: String?): Boolean {
                adapt.filter.filter(input)
                return false
            }
        })

        return flater
    }

    override fun onResume() {
        super.onResume()
        if(this::ingredientRecycler.isInitialized) {
            ingredientRecycler.adapter = IngredientRecyclerAdapter(Ingredient.ingredientList)
            ingredientRecycler.invalidate()
        }
    }
}