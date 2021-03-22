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

    override fun getItemCount(): Int {
        return cocktails.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        mcxt = parent.context
        val inflater = LayoutInflater.from(mcxt)
        val view: View = inflater.inflate(R.layout.fragment_browse_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.ivImage.setImageResource(cocktails[position].image)
        holder.tvName.text = cocktails[position].name
        holder.tvBlurb.text = cocktails[position].recipeBlurb
        holder.ratRatingBar.numStars = cocktails[position].rating

        if (cocktails[position].isFavourite) {
            holder.ivFavourite.setImageResource(R.mipmap.icon_star_on_foreground)
        } else {
            holder.ivFavourite.setImageResource(R.mipmap.icon_star_off_foreground)
        }
    }

    class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.findViewById(R.id.cocktail_image)
        var tvName: TextView = itemView.findViewById(R.id.cocktail_name)
        var tvBlurb: TextView = itemView.findViewById(R.id.cocktail_blurb)
        var ratRatingBar: RatingBar = itemView.findViewById(R.id.cocktail_rating)
        var ivFavourite: ImageView = itemView.findViewById(R.id.cocktail_favourite)
    }
}
