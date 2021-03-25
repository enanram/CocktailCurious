package com.k2eb.cocktailcurious

class MockDatabase {
	var virtualCupboard: MutableList<Ingredient>
	var equipmentList: MutableList<Equipment>
	var allIngredients: MutableList<Ingredient>
	var allRecipes: MutableList<CocktailRecipe>
	var favourites: MutableList<CocktailRecipe>
	var yourMenu: MutableList<CocktailRecipe>

	init {
		virtualCupboard = mutableListOf()
		equipmentList = mutableListOf()
		allIngredients = mutableListOf()
		allRecipes = mutableListOf()
		favourites = mutableListOf()
		yourMenu = mutableListOf()
	}

	/**
	 * adds ingredient to users virtual cupboard
	 */
	fun addToCupboard(ingredient: Ingredient) {
		virtualCupboard.add(ingredient)
	}

	/**
	 * removes ingredient from users virtual cupboard
	 */
	fun removeFromCupboard(ingredient: Ingredient) {
		virtualCupboard.remove(ingredient)
	}

	/**
	 * adds equipment to list of equipment
	 */
	fun addEquipment(equipment: Equipment) {
		equipmentList.add(equipment)
	}

	/**
	 * removes equipment from equipment list
	 */
	fun removeEquipment(equipment: Equipment) {
		equipmentList.remove(equipment)
	}

	/**
	 * add ingredient to ingredient list
	 */
	fun addIngredient(ingredient: Ingredient) {
		allIngredients.add(ingredient)
	}

	/**
	 * remove ingredient from ingredient list
	 */
	fun removeIngredient(ingredient: Ingredient) {
		allIngredients.remove(ingredient)
	}

	/**
	 * adds a cocktail recipe to the cocktail recipe app
	 */
	fun addRecipe(recipe: CocktailRecipe) {
		allRecipes.add(recipe)
	}

	/**
	 * removes a cocktail from the cocktail recipe app
	 */
	fun removeRecipe(recipe: CocktailRecipe) {
		allRecipes.remove(recipe)
	}

	/**
	 * add a cocktail recipe to favourites list
	 */
	fun addToFavourites(cocktailRecipe: CocktailRecipe) {
		favourites.add(cocktailRecipe)
	}

	/**
	 * remove a cocktail recipe from your favourites list
	 */
	fun removeFromFavourites(cocktailRecipe: CocktailRecipe) {
		favourites.remove(cocktailRecipe)
	}

}