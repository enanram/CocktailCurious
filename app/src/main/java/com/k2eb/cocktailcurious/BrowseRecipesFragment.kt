package com.k2eb.cocktailcurious

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesFragment : Fragment() {

    private lateinit var recipeAdapter: BrowseRecipesRecyclerAdapter
    lateinit var recipeRecycler: RecyclerView
    private var cocktailList = arrayListOf<CocktailRecipe>()
    var favouritesList = mutableListOf<CocktailRecipe>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val flater = inflater.inflate(R.layout.fragment_browse_recipes, container, false)
        recipeRecycler = flater.findViewById(R.id.recipe_results_recycler)
        recipeRecycler.layoutManager = LinearLayoutManager(recipeRecycler.context)
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

    /**
     * Just some string to stick into the card view. Nothing major.
     */

    private fun makeDummyList() {

//        val bluLag = CocktailRecipe("Blue Lagoon", "Refreshing and blue.")
//        val pinCol = CocktailRecipe("Pina Colada", "If you like 'em, and rain too.")
//        val mojito = CocktailRecipe("Mojito", "Minty fresh goodness!")
//        val maiTai = CocktailRecipe("Mai Tai", "Camp and fruity. You are what you drink.")
//        val grasshop = CocktailRecipe("Grasshopper", "Like mint choc chip ice cream!")



//        bluLag.image = R.drawable.the_blue_lagoon_cocktail
//        bluLag.description = "The Blue Lagoon is a refreshing, three-ingredient cocktail containing vodka, blue curaçao and lemonade."
//        pinCol.description = "This tropical cocktail — a mix of rum, coconut, pineapple and lime juices—dates to the 1950s and has been satisfying vacationers and Tiki aficionados since."
//        mojito.addToFavourites()
//        pinCol.addToFavourites()
//        cocktailList.add(bluLag)
//        cocktailList.add(pinCol)
//        cocktailList.add(mojito)
//        cocktailList.add(maiTai)
//        cocktailList.add(grasshop)
//
//        for (item in cocktailList) {
//            if (item.isFavourite) {
//                favouritesList.add(item)
//            }
//        }
    }
}
