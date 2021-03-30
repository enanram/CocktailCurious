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

class FavouritesFragment : Fragment() {

    lateinit var recipeRecycler: RecyclerView
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
        val flater = inflater.inflate(R.layout.fragment_favourites, container, false)
        recipeRecycler = flater.findViewById(R.id.favourite_results_recycler)
        recipeRecycler.layoutManager = LinearLayoutManager(recipeRecycler.context)

        // get data function
        CocktailRecipe.cocktailList.forEach {
            if (it.isFavourite) favouritesList.add(it)
        }
        recipeRecycler.adapter = BrowseRecipesRecyclerAdapter(favouritesList)

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

