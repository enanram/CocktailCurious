package com.k2eb.cocktailcurious

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class IngredientRecyclerAdapter(
        private var ingredients: List<Ingredient>
): RecyclerView.Adapter<IngredientRecyclerAdapter.IngredientViewHolder>() {

    lateinit var mcxt: Context

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        mcxt = parent.context
        val inflater = LayoutInflater.from(mcxt)
        val view: View = inflater.inflate(R.layout.fragment_search_card, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.tvName.text = ingredients[position].name

        holder.cardView.setOnClickListener {
            holder.check.isChecked != holder.check.isChecked
            if (holder.check.isChecked) {
                VirtualCupboardFragment.addIngredient(ingredients[position])
            }
        }
    }

    class IngredientViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            var cardView: CardView = itemView.findViewById(R.id.card_ingredient)
            var check: CheckBox = itemView.findViewById(R.id.ingredient_can_has)
            var tvName: TextView = itemView.findViewById(R.id.ingredient_name)
    }
}