package com.k2eb.cocktailcurious

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.k2eb.cocktailcurious.MainActivity.Companion.cupboardList

class YourMenuFragment : Fragment() {

    lateinit var recipeRecycler: RecyclerView
    private var yourMenu = mutableListOf<CocktailRecipe>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if(this::recipeRecycler.isInitialized) {
            recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(yourMenu)
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
        menuFilter()
        recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(yourMenu)

        return flater
    }

    private fun menuFilter() {
        for (cocktail in CocktailRecipe.cocktailList){
            if (cupboardList.containsAll(cocktail.ingredients.keys.toList())) {
                yourMenu.add(cocktail)
            }
        }
    }
}