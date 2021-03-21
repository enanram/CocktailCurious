package com.k2eb.cocktailcurious

import java.lang.IllegalArgumentException

class CocktailRecipe(db: MockDatabase, recipeName: String, instruct: String, equipList: List<Equipment>) {
	var isFavourite: Boolean
	var database: MockDatabase
	var name: String
	var rating: Int
	var instructions: String
	var equipment: List<Equipment> = mutableListOf()
	//var ingredients: List<Map<Ingredient,Int>> = mutableListOf()
	var ingredients: Map<Ingredient,Int> = mutableMapOf()

	init {
		isFavourite = false
		database = db
		name = recipeName
		rating = 0
		equipment = equipList
		instructions = instruct
	}

	//fun addIngredients(inputIngredient: Ingredient, Amount: Int){
		//do loop
		//var index: Int

		//if(ingredients(Ingredient).contains(inputIngredient)) {
			//throw IllegalArgumentException("Item already in list")
		//} else {
			//ingredients(1(1)) = Amount
		//}
	//}

	//if this cocktail recipe item is not held within the favourites list of the database class
	//then it will be added
	fun addToFavourites() {
		if (database.favourites.contains(this)) {
			throw IllegalArgumentException("Item already in list")
		} else {
			isFavourite = true
			database.addToFavourites(this)
		}
	}

	//depending on whether this is already contained within the favourites list within the database
	//this cocktail recipe object will be removed from within it
	fun removeFromFavourites() {
		if (!database.favourites.contains(this)) {
			throw NoSuchElementException("Item wasn't found in list")
		} else {
			isFavourite = false
			database.removeFromFavourites(this)
		}
	}


}