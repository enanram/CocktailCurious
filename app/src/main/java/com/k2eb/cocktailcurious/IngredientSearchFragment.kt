package com.k2eb.cocktailcurious

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IngredientSearchFragment : Fragment() {

    lateinit var ingredientRecycler: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val flater: View = LayoutInflater.from(activity).inflate(R.layout.fragment_search_popup, container,false)
        ingredientRecycler = flater.findViewById(R.id.ingredient_results)
        ingredientRecycler.layoutManager = LinearLayoutManager(ingredientRecycler.context)
        ingredientRecycler.adapter = IngredientRecyclerAdapter(MainActivity.makeIngredientsList())

        val exitBtn = flater.findViewById<Button>(R.id.btn_x)
        exitBtn.setOnClickListener{
            val transaction = this.fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, VirtualCupboardFragment())
            transaction?.addToBackStack(null)
            (activity as MainActivity).setTitleBar("Virtual Cupboard")
            transaction?.commit()
        }

        return flater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}