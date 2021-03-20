package com.k2eb.cocktailcurious

import java.lang.IllegalArgumentException

class CocktailRecipe(db: MockDatabase) {
	var isFavourite: Boolean
	var database: MockDatabase

	init {
		isFavourite = false
		database = db
	}

	fun addToFavourites() {
		if (database.favourites.contains(this)) {
			throw IllegalArgumentException("Item already in list")
		} else {
			isFavourite = true
			database.addToFavourites(this)
		}
	}

	fun removeFromFavourites() {
		if (!database.favourites.contains(this)) {
			throw NoSuchElementException("Item wasn't found in list")
		} else {
			isFavourite = false
			database.removeFromFavourites(this)
		}
	}


}