package com.k2eb.cocktailcurious

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesFragment : Fragment() {

    private lateinit var recipeAdapter: BrowseRecipesRecyclerAdapter
    lateinit var recipeRecycler: RecyclerView
    private var cocktailList = arrayListOf<CocktailRecipe>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card: Button? = view.findViewById(R.id.card_cocktail)
//        card?.setOnClickListener {
//            intent = Intent(activity, RecipeActivity::class.java)
////            var title: TextView = view.findViewById(R.id.cocktail_name)
////            cocktailList.forEach {
////                if (it.name == title.)
////            }
//            startActivity(intent)
//        }

        val pButton: Button? = view.findViewById(R.id.temp_blue_lagoon_button)
        pButton?.setOnClickListener {
            val intent = Intent(activity, RecipeActivity::class.java)
//            intent.putExtra("Blue Lagoon", cocktailList[0])
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
        val bluLag = CocktailRecipe("Blue Lagoon", "Refreshing and blue")
        val pinCol = CocktailRecipe("Pina Colada", "If you like 'em, and rain too")
        cocktailList.add(bluLag)
        cocktailList.add(pinCol)

        recipeAdapter = BrowseRecipesRecyclerAdapter(cocktailList)
        recipeRecycler.adapter = recipeAdapter
    }
}