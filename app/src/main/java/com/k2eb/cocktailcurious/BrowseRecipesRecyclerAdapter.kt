package com.k2eb.cocktailcurious

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class BrowseRecipesRecyclerAdapter(
    private var cocktails: List<CocktailRecipe>
) :
    RecyclerView.Adapter<BrowseRecipesRecyclerAdapter.RecipeViewHolder>() {

    lateinit var mcxt: Context

    /**
     * returns the size of the cocktail list
     */
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
        holder.tvBlurb.text = cocktails[position].blurb
        holder.ratRatingBar.numStars = cocktails[position].rating

        if (cocktails[position].isFavourite) {
            holder.ivFavourite.setImageResource(R.mipmap.icon_star_on_foreground)
        } else {
            holder.ivFavourite.setImageResource(R.mipmap.icon_star_off_foreground)
        }

        holder.cardView.setOnClickListener {
            val intent = Intent(mcxt, RecipeActivity::class.java)
            intent.putExtra("recipe index", cocktails[position].name)
            mcxt.startActivity(intent)
        }
    }

    class RecipeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.card_cocktail)
        var ivImage: ImageView = itemView.findViewById(R.id.cocktail_image)
        var tvName: TextView = itemView.findViewById(R.id.cocktail_name)
        var tvBlurb: TextView = itemView.findViewById(R.id.cocktail_blurb)
        var ratRatingBar: RatingBar = itemView.findViewById(R.id.cocktail_rating)
        var ivFavourite: ImageView = itemView.findViewById(R.id.cocktail_favourite)
    }

}
