package com.k2eb.cocktailcurious

import android.os.Parcel
import android.os.Parcelable
import java.lang.IllegalArgumentException

class CocktailRecipe(
		recipeName: String?,
		recipeBlurb: String?
) : Parcelable {
	var name = recipeName
	var image = R.drawable.martini_silhouette
	var blurb = recipeBlurb
	var description = ""
	var isFavourite = false
	// Rating out of 5 - 0 means no rating
	var rating = 0
	var equipment: List<Equipment> = mutableListOf()
	var ingredients = mutableMapOf<Ingredient, Int>()
	var image = R.drawable.martini_silhouette
	var instructions = mutableListOf<String>()

	constructor(parcel: Parcel) : this(
			parcel.readString(),
			parcel.readString()) {
		isFavourite = parcel.readByte() != 0.toByte()
		rating = parcel.readInt()
	}

	fun quantityString() {

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

	/**
	 * if this cocktail recipe item is not held within the favourites list of the database class
	 * then it will be added
	 */
//@Throws(IllegalArgumentException::class)
	fun addToFavourites() {
//		if (database.favourites.contains(this)) {
//			throw IllegalArgumentException("Item already in list")
//		} else {
		isFavourite = true
//			database.addToFavourites(this)
//		}
	}

	/**
	 * depending on whether this is already contained within the favourites list within the database
	 * this cocktail recipe object will be removed from within it
	 */
//	@Throws(IllegalArgumentException::class)
	fun removeFromFavourites() {
//		if (!database.favourites.contains(this)) {
//			throw NoSuchElementException("Item wasn't found in list")
//		} else {
		isFavourite = false
//			database.removeFromFavourites(this)
//		}
	}

	/**
	 * each element of the object that will be used by other classes is placed within a parcel
	 */
	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeInt(image)
		parcel.writeString(blurb)
		parcel.writeString(description)
		parcel.writeByte(if (isFavourite) 1 else 0)
		parcel.writeInt(rating)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<CocktailRecipe> {
		override fun createFromParcel(parcel: Parcel): CocktailRecipe {
			return CocktailRecipe(parcel)
		}

		override fun newArray(size: Int): Array<CocktailRecipe?> {
			return arrayOfNulls(size)
		}
	}

}