package com.k2eb.cocktailcurious

const val DEFAULT_ABV = 40

class Alcohol(name: String) : Ingredient(name) {
	var brands: List<String> = mutableListOf()
	var abv: Int = DEFAULT_ABV

	/**
	 * Secondary constructor, which takes a string array of brand names, and an integer for abv
	 */
	constructor(name: String, vararg brandNames: String, strength: Int): this(name) {
		abv = strength
		brands = mutableListOf(*brandNames)
	}

}