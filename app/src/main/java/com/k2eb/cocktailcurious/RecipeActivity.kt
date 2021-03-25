package com.k2eb.cocktailcurious

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toolbar

// a default title for the activity, in case of difficulty getting the name from the database
const val DEFAULT_RECIPE_NAME = "Recipe Name"
const val DEFAULT_DESCRIPTION = "Recipe description"
lateinit var recipe: CocktailRecipe

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Get parceled recipe object from intent
        recipe = intent.getParcelableExtra<CocktailRecipe>("recipeToShow")!!

        var iv_picture: ImageView = findViewById(R.id.recipe_image)
        var iv_favourite: ImageView = findViewById(R.id.recipe_favourite_icon)
        var iv_share: ImageView = findViewById(R.id.recipe_share_icon)
        var tv_description: TextView = findViewById(R.id.recipe_description)
        var tv_ingredients: TextView = findViewById(R.id.recipe_ingredients)
        var tv_equipment: TextView = findViewById(R.id.recipe_equipment)
        var ratBar_rating: RatingBar = findViewById(R.id.recipe_rating)


        // adds the back button in the title bar
        supportActionBar?.title = recipe.name ?: DEFAULT_RECIPE_NAME
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        iv_picture = findViewById(recipe.image ?: R.drawable.martini_silhouette)
        tv_description.text = recipe.description ?: recipe.blurb

        var ingredientString = ""
        for (ingredient in recipe.ingredients.keys) {
            ingredientString += quantityString(recipe.ingredients[ingredient]!!)

            ingredientString += ingredient.name + "/n"
        }


        if (recipe!!.isFavourite) iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)


        iv_favourite.setOnClickListener {
            toggleFavouriteButton(iv_favourite)
        }


        tv_description.setText(recipe.description)
        //tv_ingredients.setText(recipe.ingredients)
        //tv_equipment
        //ratBar_rating.setOnClickListener(


    }

    fun toggleFavouriteButton(iv_favourite: ImageView) {
        if(recipe.isFavourite) {
            recipe.removeFromFavourites()
            iv_favourite.setImageResource(R.mipmap.icon_star_off_foreground)
        } else {
            recipe.addToFavourites()
            iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)
        }
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
            else -> "$value ml of "
        }
    }


}
