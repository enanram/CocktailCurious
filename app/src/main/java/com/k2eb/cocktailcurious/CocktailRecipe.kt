package com.k2eb.cocktailcurious

import androidx.versionedparcelable.VersionedParcelize
import java.lang.IllegalArgumentException


class CocktailRecipe(
		var name: String,
		var image: Int,
		var blurb: String,
		var description: String,
		var equipment: MutableList<Equipment>,
		var ingredients: MutableMap<Ingredient, Int>,
		var instructions: MutableList<String>
)  {
	var isFavourite = false
	var rating = 0


	fun toggleFavourite() {
		isFavourite = !isFavourite
	}

	/**
	 * sets the rating which should be between 1 and 5. 0 means no rating.
	 */
	@Throws(IllegalArgumentException::class)
	fun changeRating(value: Int) {
		if (value > 5 || value < 0) {
			throw IllegalArgumentException("Value must be a number between 0 and 5 inclusive.")
		} else {
			rating = value
		}
	}

	/**
	 * Adds ingredient and amount to the ingredient list. If the ingredient is already in the list,
	 * updates the amount.
	 */
	fun addIngredientAndAmount(ingredient: Ingredient, quantity: Int) {
		if (ingredients.containsKey(ingredient)) {
			println("Ingredient already in recipe - updated amount.")
		}
		ingredients.put(ingredient, quantity)

	}


	companion object {
		val cocktailList = mutableListOf<CocktailRecipe>()
	}
}