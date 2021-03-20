package com.k2eb.cocktailcurious

class DrinkMenu(db: MockDatabase) {
	var listOfRecipes: MutableList<CocktailRecipe>
	var userCupboard: MutableList<Ingredient>
	var database: MockDatabase
	var userFavourites: MutableList<CocktailRecipe>


	init {
		database = db
		listOfRecipes = database.allRecipes
		userCupboard = database.virtualCupboard
		userFavourites = database.favourites
	}


}