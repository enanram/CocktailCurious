package com.k2eb.cocktailcurious

import com.k2eb.cocktailcurious.MainActivity.Companion.cupboardList

open class Ingredient(var name: String?)  {

    var checked: Boolean = false

    fun checkToCupboard() {
        this.checked = !this.checked
    }

    companion object {
        val ingredientList = mutableListOf<Ingredient>()
    }
}