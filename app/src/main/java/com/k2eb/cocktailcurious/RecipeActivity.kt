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

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        var iv_picture: ImageView = findViewById(R.id.recipe_image)
        var iv_favourite: ImageView = findViewById(R.id.recipe_favourite_icon)
        var iv_share: ImageView = findViewById(R.id.recipe_share_icon)
        var tv_description: TextView = findViewById(R.id.recipe_description)
        var tv_ingredients: TextView = findViewById(R.id.recipe_ingredients)
        var tv_equipment: TextView = findViewById(R.id.recipe_equipment)
        var ratBar_rating: RatingBar = findViewById(R.id.recipe_rating)

        val recipe = intent.getParcelableExtra<CocktailRecipe>("recipeToShow")


        // adds the back button in the title bar
        supportActionBar?.title = recipe?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        iv_picture.setImageResource(recipe!!.image)
        if (recipe.isFavourite) {
            iv_favourite.setImageResource(R.mipmap.icon_star_on_foreground)
        } else {
            iv_favourite.setImageResource(R.mipmap.icon_star_off_foreground)
        }

        tv_description.setText(recipe?.description)
        //tv_ingredients.setText(recipe.ingredients)
        //tv_equipment
        //ratBar_rating.setOnClickListener(


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }



}
