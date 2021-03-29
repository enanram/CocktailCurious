package com.k2eb.cocktailcurious

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import java.lang.NullPointerException

// a default title for the activity, in case of difficulty getting the name from the database
const val DEFAULT_RECIPE_NAME = "Recipe Name"
const val DEFAULT_DESCRIPTION = "Recipe description"

class RecipeActivity : AppCompatActivity() {
    lateinit var recipe: CocktailRecipe
    var defaultRecipeImage = R.drawable.martini_silhouette

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)


        // Get recipe object from intent via name string
        val recipeName = intent.getStringExtra("recipe index")
        recipe = findRecipeByName(recipeName!!)


        var iv_picture: ImageView = findViewById(R.id.recipe_image)
        var iv_favourite: ImageView = findViewById(R.id.recipe_favourite_icon)
        var iv_share: ImageView = findViewById(R.id.recipe_share_icon)
        var tv_description: TextView = findViewById(R.id.recipe_description)
        var tv_ingredients: TextView = findViewById(R.id.recipe_ingredients)
        var tv_equipment: TextView = findViewById(R.id.recipe_equipment)
        var ratBar_rating: RatingBar = findViewById(R.id.recipe_rating)
        var instructions: TextView = findViewById(R.id.recipe_instructions)


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

        if (recipe.isFavourite) iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)


//        iv_favourite.setOnClickListener {
//            toggleFavouriteButton(iv_favourite)
//        }


        tv_description.text = recipe.description
        //tv_ingredients.setText(recipe.ingredients)
        //tv_equipment
        //ratBar_rating.setOnClickListener(

        instructions.text = formatInstructions(recipe.instructions)


    }

    /**
     * when pressed, if the recipe object is held within the favourites list already, it is removed
     * if it is not held within, it is added
     */
    private fun toggleFavouriteButton(iv_favourite: ImageView) {
        if(recipe.isFavourite) {
            recipe.removeFromFavourites()
            iv_favourite.setImageResource(R.mipmap.icon_star_off_foreground)
        } else {
            recipe.addToFavourites()
            iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)
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
            -7 -> "Sprig of "
            -8 -> "Dash of "
            -9 -> "Drizzle of "
            -10 -> "Splash of "
            -11 -> "Teaspoon of "
            -12 -> "2 teaspoons of "
            -13 -> "Juice of one "

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

    fun findRecipeByName(rName: String): CocktailRecipe {
        var cocktailToReturn: CocktailRecipe? = null
        CocktailRecipe.cocktailList.forEach {
            if (it.name == rName) cocktailToReturn = it
        }
        if (cocktailToReturn == null) throw NoSuchElementException("Recipe not found.")
        return cocktailToReturn!!
    }
}
