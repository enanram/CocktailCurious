package com.k2eb.cocktailcurious

class CocktailRecipe(
    private var cocktailName: String,
    private var cocktailDescription: String,
    private var cocktailRating: Int
) {
    var isFavourite: Boolean = false
    var cocktailIngredients = arrayListOf<Ingredient>()

    private fun toggleFavourite(): Boolean {
        this.isFavourite = !isFavourite
        return isFavourite
    }

    private fun addIngredient(ingredient: Ingredient) {
        cocktailIngredients.add(ingredient)
    }
}