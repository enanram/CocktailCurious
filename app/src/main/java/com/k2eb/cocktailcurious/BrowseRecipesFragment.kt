package com.k2eb.cocktailcurious

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesFragment : Fragment() {

    private lateinit var recipeAdapter: BrowseRecipesRecyclerAdapter
    lateinit var recipeRecycler: RecyclerView
    private var cocktailList = arrayListOf<CocktailRecipe>()
    var favouritesList = mutableListOf<CocktailRecipe>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card: Button? = view.findViewById(R.id.card_cocktail)
        card?.setOnClickListener {
            val intent = Intent(activity, RecipeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val flater = inflater.inflate(R.layout.fragment_browse_recipes, container, false)
        recipeRecycler = flater.findViewById(R.id.recipe_results_recycler)
        recipeRecycler.layoutManager = LinearLayoutManager(recipeRecycler.context)

        // Insert
        makeDummyList()
        recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(cocktailList)

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

        val db = MockDatabase()
        val bluLag = CocktailRecipe(db, "Blue Lagoon", "Refreshing and blue.")
        val pinCol = CocktailRecipe(db, "Pina Colada", "If you like 'em, and rain too.")
        val mojito = CocktailRecipe(db, "Mojito", "Minty fresh goodness!")
        val maiTai = CocktailRecipe(db, "Mai Tai", "Camp and fruity. You are what you drink.")
        val grasshop = CocktailRecipe(db, "Grasshopper", "Like mint choc chip ice cream!")
        mojito.addToFavourites()
        pinCol.addToFavourites()
        cocktailList.add(bluLag)
        cocktailList.add(pinCol)
        cocktailList.add(mojito)
        cocktailList.add(maiTai)
        cocktailList.add(grasshop)

        for (item in cocktailList) {
            if (item.isFavourite) {
                favouritesList.add(item)
            }
        }
    }
}
