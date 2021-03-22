package com.k2eb.cocktailcurious

class CocktailRecipe(
    private var cocktailName: String,
    private var cocktailBlurb: String
) {
    private var cocktailRating: Int = 0
    lateinit var cocktailDescription: String
    var isFavourite: Boolean = false
    var cocktailIngredients = arrayListOf<Ingredient>()

    private fun addRating(rating: Int) {
        if (rating in 0..5) {
            this.cocktailRating = rating
        }
    }

    private fun addDescription(description: String){
        cocktailDescription = description
    }

    private fun toggleFavourite(): Boolean {
        this.isFavourite = !isFavourite
        return isFavourite
    }

    private fun addIngredient(ingredient: Ingredient) {
        cocktailIngredients.add(ingredient)
    }
}