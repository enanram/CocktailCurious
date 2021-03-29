package com.k2eb.cocktailcurious

import android.os.Parcel
import android.os.Parcelable

open class Ingredient(var name: String?)  {


    companion object {
        val ingredientList = mutableListOf<Ingredient>()
    }
}