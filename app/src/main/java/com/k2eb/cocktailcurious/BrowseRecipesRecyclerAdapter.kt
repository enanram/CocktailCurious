package com.k2eb.cocktailcurious

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesRecyclerAdapter(
    private var cocktails: List<CocktailRecipe>
) :
    RecyclerView.Adapter<BrowseRecipesRecyclerAdapter.RecipeViewHolder>() {

    lateinit var mcxt: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        mcxt = parent.context
        val inflater = LayoutInflater.from(mcxt)
        val view: View = inflater.inflate(R.layout.fragment_browse_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.tvName.text = cocktails[position].name
        holder.tvBlurb.text = cocktails[position].recipeBlurb

    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.findViewById(R.id.cocktail_image)
        var tvName: TextView = itemView.findViewById(R.id.cocktail_name)
        var tvBlurb: TextView = itemView.findViewById(R.id.cocktail_blurb)
        var ratRatingBar: RatingBar = itemView.findViewById(R.id.cocktail_rating)

    }

}
