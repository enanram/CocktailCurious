package com.k2eb.cocktailcurious

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class IngredientRecyclerAdapter(
        private var ingredients: MutableList<Ingredient>
): RecyclerView.Adapter<IngredientRecyclerAdapter.IngredientViewHolder>(), Filterable {

    lateinit var mcxt: Context
    var filterList = ingredients

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

        holder.check.isChecked = ingredients[position].checked
        holder.cardView.setOnClickListener{
            holder.check.isChecked = !holder.check.isChecked
            ingredients[position].checkToCupboard()
        }
   }

    class IngredientViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            var cardView: CardView = itemView.findViewById(R.id.card_ingredient)
            var check: CheckBox = itemView.findViewById(R.id.ingredient_can_has)
            var tvName: TextView = itemView.findViewById(R.id.ingredient_name)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    ingredients
                } else {
                    val resultList = ArrayList<Ingredient>()
                    for (item in ingredients) {
                        if (item.name!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(item)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Ingredient>
                notifyDataSetChanged()
            }

        }
    }
}