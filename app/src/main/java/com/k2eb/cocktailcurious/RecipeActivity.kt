package com.k2eb.cocktailcurious

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import java.lang.NullPointerException
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast

// a default title for the activity, in case of difficulty getting the name from the database
const val DEFAULT_RECIPE_NAME = "Recipe Name"
const val DEFAULT_DESCRIPTION = "Recipe description"

class RecipeActivity : AppCompatActivity() {
    lateinit var recipe: CocktailRecipe
    var defaultRecipeImage = R.drawable.martini_silhouette
    lateinit var iv_favourite: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)


        // Get recipe object from intent via name string
        val recipeName = intent.getStringExtra("recipe index")
        recipe = findRecipeByName(recipeName!!)


        var iv_picture: ImageView = findViewById(R.id.recipe_image)
        iv_favourite = findViewById(R.id.recipe_favourite_icon)
        var iv_share: ImageView = findViewById(R.id.recipe_share_icon)
        var tv_description: TextView = findViewById(R.id.recipe_description)
        var tv_ingredients: TextView = findViewById(R.id.recipe_ingredients)
        var tv_equipment: TextView = findViewById(R.id.recipe_equipment)
        var instructions: TextView = findViewById(R.id.recipe_instructions)
        val rBar = findViewById<RatingBar>(R.id.recipe_rating)
        val btn = findViewById<Button>(R.id.recipe_submit)


        // adds the back button in the title bar
        supportActionBar?.title = recipe.name ?: DEFAULT_RECIPE_NAME
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            iv_picture.setImageResource(recipe.image)
        } catch (e: NullPointerException) {
            iv_picture.setImageResource(findViewById(defaultRecipeImage))
        }
        tv_description.text = if (recipe.description == "") recipe.blurb else recipe.description

        tv_ingredients.text = formatIngredients(recipe.ingredients)

        tv_equipment.text = formatEquipment(recipe.equipment)



        updateFavouriteImage()


        iv_favourite.setOnClickListener {
            toggleFavouriteButton()
        }

        iv_share.setOnClickListener {
            shareToSocials()
        }


        tv_description.text = recipe.description
        //tv_ingredients.setText(recipe.ingredients)
        //tv_equipment
        //ratBar_rating.setOnClickListener(

        instructions.text = formatInstructions(recipe.instructions)

        rBar.numStars = recipe.rating
        btn!!.setOnClickListener {
            val getrating = rBar!!.rating
            Toast.makeText(this, "Rating Submitted: $getrating", Toast.LENGTH_LONG).show()
            recipe.rating = getrating.toInt()
        }

    }

    /**
     * when pressed, if the recipe object is held within the favourites list already, it is removed
     * if it is not held within, it is added
     */
    private fun toggleFavouriteButton() {
        recipe.isFavourite = !recipe.isFavourite
        updateFavouriteImage()
    }

    private fun updateFavouriteImage() {
        if(recipe.isFavourite) {
            iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)
        } else {
            iv_favourite.setImageResource(R.mipmap.icon_star_off_foreground)
        }
    }

    private fun shareToSocials() {
        Toast.makeText(this, "Shared to social media!", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    /**
     * Returns the amount of an ingredient in string form, with certain numbers reserved for
     * specific descriptive words
     */
    private fun quantityString(value: Int): String {
        return when(value) {
            -1 -> "Pinch of "
            -2 -> "Bunch of "
            -3 -> "Wedge of "
            -4 -> "Sprig of "
            -5 -> "Zest of "
            -6 -> "Squeeze of "
            -7 -> "Slice of "
            -8 -> "Twist of "
            -9 -> "Drizzle of "
            -10 -> "Splash of "
            -11 -> "1 teaspoon of "
            -12 -> "2 teaspoons of "
            -13 -> "Juice of one "
            -14 -> "Small handful of "
            -15 -> "Juice of half a "
            -16 -> "1 tablespoon of "
            -17 -> "2 tablespoons of "
            -18 -> "Dash of "
            -19 -> "2 dashes of "
            -20 -> "3 dashes of "
            -21 -> "4 dashes of "
            -22 -> "5 dashes of "
            -23 -> "6 dashes of "
            -24 -> "Halved "
            -25 -> "Half of "
            -26 -> "Small peel of an "
            -27 -> "Juice of three "

            -30 -> "Scoop of "
            -31 -> "200g of "
            -32 -> "500g of "

            -101 -> "One "
            -102 -> "Two "
            -103 -> "Three "
            -104 -> "Four "
            -105 -> "Five "
            -106 -> "Six "
            -107 -> "Seven "
            -108 -> "Eight "
            -109 -> "Nine "
            -110 -> "Ten "

            else -> "$value ml of "
        }
    }


    /**
     * converts a list of strings to a string with each element on a new, numbered line
     */
    fun formatInstructions(instructionList: MutableList<String>): String {
        // instructionsf stands for 'instructions formatted'
        var instructionsf = ""

        if(instructionList.size == 1) {
            instructionsf = instructionList[0]
        } else {
            instructionList.forEachIndexed { i, line ->
                val num = i + 1
                instructionsf += "$num. $line \n"
            }
        }
        // remove the newline from the last instruction
        return instructionsf.trimEnd()

    }

    fun formatIngredients(ingredients: MutableMap<Ingredient, Int>): String {
        var ingredientsf = ""
        ingredients.forEach { ingredient ->
            ingredientsf += quantityString(ingredient.value)
            ingredientsf += ingredient.key.name!!.toLowerCase() + "\n"
        }
        return ingredientsf.trimEnd()
    }

    fun formatEquipment(equipList:MutableList<Equipment>): String {
        var equipString = ""
        if (equipList.isEmpty()) {
            equipString = "None"
        } else {
            equipList.forEach {
                equipString += it.name
                equipString += "\n"
            }
        }
        return equipString.trimEnd()
    }

    fun findRecipeByName(rName: String): CocktailRecipe {
        var cocktailToReturn: CocktailRecipe? = null
        CocktailRecipe.cocktailList.forEach {
            if (it.name == rName) cocktailToReturn = it
        }
        if (cocktailToReturn == null) throw NoSuchElementException("Recipe not found.")
        return cocktailToReturn!!
    }

    fun onCreateRecipeRating(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

    }
}
