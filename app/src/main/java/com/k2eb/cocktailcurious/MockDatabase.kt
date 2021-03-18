package com.k2eb.cocktailcurious

class MockDatabase {
	var virtualCupboard: MutableList<Ingredient>
	var equipmentList: MutableList<Equipment>
	var allIngredients: MutableList<Ingredient>
	var allRecipes: MutableList<CocktailRecipe>

	init {
		virtualCupboard = mutableListOf()
		equipmentList = mutableListOf()
		allIngredients = mutableListOf()
		allRecipes = mutableListOf()
	}

	fun addToCupboard(ingredient: Ingredient) {
		virtualCupboard.add(ingredient)
	}

	fun removeFromCupboard(ingredient: Ingredient) {
		virtualCupboard.remove(ingredient)
	}

	fun addEquipment(equipment: Equipment) {
		equipmentList.add(equipment)
	}

	fun removeEquipment(equipment: Equipment) {
		equipmentList.remove(equipment)
	}

	fun addIngredient(ingredient: Ingredient) {
		allIngredients.add(ingredient)
	}

	fun removeIngredient(ingredient: Ingredient) {
		allIngredients.remove(ingredient)
	}

	fun addRecipe(recipe: CocktailRecipe) {
		allRecipes.add(recipe)
	}

	fun removeRecipe(recipe: CocktailRecipe) {
		allRecipes.remove(recipe)
	}

}