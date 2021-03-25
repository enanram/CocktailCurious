package com.k2eb.cocktailcurious

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar

// a default title for the activity, in case of difficulty getting the name from the database
const val DEFAULT_RECIPE_NAME = "Recipe Name"

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipe = intent.getParcelableExtra<CocktailRecipe>("recipeToShow")




        // adds the back button in the title bar
        supportActionBar?.title = recipe?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    // test comment

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }



}