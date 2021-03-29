package com.k2eb.cocktailcurious

import android.os.Parcel
import android.os.Parcelable

open class Ingredient(var name: String?)  {

    var checked: Boolean = false


    companion object {
        val ingredientList = mutableListOf<Ingredient>()
        var cupboardList = mutableListOf<Ingredient>()

    }
}