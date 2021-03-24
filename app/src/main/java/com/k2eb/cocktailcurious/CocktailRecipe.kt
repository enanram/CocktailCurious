package com.k2eb.cocktailcurious

import android.os.Parcel
import android.os.Parcelable
import java.lang.IllegalArgumentException

class CocktailRecipe(
//		db: MockDatabase,
		recipeName: String,
		blurb: String
//		instruct: String,
//		equipList: List<Equipment>
) : Parcelable {
	var isFavourite: Boolean
//	var database: MockDatabase
	var name: String?
	var recipeBlurb: String?
	// Rating out of 5 - 0 means no rating
	var rating: Int
//	var instructions: String
//	var equipment: List<Equipment> = mutableListOf()
	var ingredients: MutableMap<Ingredient, Int>

	constructor(parcel: Parcel) : this(
//		TODO("db"),
		TODO("recipeName"),
		TODO("blurb")
	) {
		isFavourite = parcel.readByte() != 0.toByte()
		name = parcel.readString()
		recipeBlurb = parcel.readString()
		rating = parcel.readInt()
	}

	init {
		isFavourite = false
//		database = db
		name = recipeName
		recipeBlurb = blurb
		rating = 0
//		equipment = equipList
//		instructions = instruct
		ingredients = mutableMapOf<Ingredient, Int>()
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
//	@Throws(IllegalArgumentException::class)
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

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeByte(if (isFavourite) 1 else 0)
		parcel.writeString(name)
		parcel.writeString(recipeBlurb)
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