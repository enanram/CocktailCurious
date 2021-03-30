package com.k2eb.cocktailcurious

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.k2eb.cocktailcurious.MainActivity.Companion.cupboardList


class CupboardRecyclerAdapter(
        private var ingredients: MutableList<Ingredient>
): RecyclerView.Adapter<CupboardRecyclerAdapter.IngredientViewHolder>() {

    lateinit var mcxt: Context

    override fun getItemCount(): Int {
        return ingredients.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        mcxt = parent.context
        val inflater = LayoutInflater.from(mcxt)
        val view: View = inflater.inflate(R.layout.fragment_virtual_cupboard_card, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {

        holder.tvName.text = ingredients[position].name

        holder.ivTrash.setOnClickListener {
            ingredients[position].checked = false
            cupboardList.remove(ingredients[position])
            notifyDataSetChanged()
            }
        }

    class IngredientViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.card_cupboard)
        var tvName: TextView = itemView.findViewById(R.id.cupboard_ingredient)
        var ivTrash: Button = itemView.findViewById(R.id.cupboard_trash)
    }
}