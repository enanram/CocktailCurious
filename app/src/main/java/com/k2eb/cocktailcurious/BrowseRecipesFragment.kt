package com.k2eb.cocktailcurious

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesFragment : Fragment() {

    private lateinit var recipeAdapter: BrowseRecipesRecyclerAdapter
    lateinit var recipeRecycler: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card: Button? = view.findViewById(R.id.card_cocktail)
    }


    override fun onResume() {
        super.onResume()
        if(this::recipeRecycler.isInitialized) {
            recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(CocktailRecipe.cocktailList)
            recipeRecycler.invalidate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Inflate the layout for this fragment
          */
        val flater = inflater.inflate(R.layout.fragment_browse_recipes, container, false)
        recipeRecycler = flater.findViewById(R.id.recipe_results_recycler)
        recipeRecycler.layoutManager = LinearLayoutManager(recipeRecycler.context)

        /**
         * Insert
          */
        recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(CocktailRecipe.cocktailList)

        return flater
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            VirtualCupboardFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}

