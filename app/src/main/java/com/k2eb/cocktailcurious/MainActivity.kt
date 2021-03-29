package com.k2eb.cocktailcurious

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.solver.widgets.Optimizer
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

const val EXTRA_MESSAGE = "com.k2eb.cocktailcurious.MESSAGE"

class MainActivity : AppCompatActivity() {


    // The hamburger button for the nav drawer
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var fragmentContainer: FrameLayout
    lateinit var fragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragment_container)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, VirtualCupboardFragment()).commit()
        setTitleBar(getString(R.string.virtual_cupboard_title))

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Adds a back arrow when the menu is open
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // add objects to relevant lists
        if (CocktailRecipe.cocktailList.isEmpty()) addCocktailsToList()
        if (Ingredient.ingredientList.isEmpty()) addIngredientsToList()
        if (Equipment.equipmentList.isEmpty()) addEquipmentToList()

        //moves the user to another fragment depending on which item is selected in the main activity
        //the activity representing the menu that appears from the side
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_virtual_cupboard -> {
//                    Toast.makeText(applicationContext, "clicked virtual cupboard", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_container, VirtualCupboardFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.virtual_cupboard_title))
                    transaction.commit()

                }
                R.id.nav_your_menu -> {
//                    Toast.makeText(applicationContext, "clicked your menu", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_container, YourMenuFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.your_menu_title))
                    transaction.commit()

                }
                R.id.nav_favourites -> {
//                    Toast.makeText(applicationContext, "clicked favourites", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FavouritesFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.favourites_title))
                    transaction.commit()

                }
                R.id.nav_browse_recipes -> {
//                    Toast.makeText(applicationContext, "clicked browse recipes", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction().replace(R.id.fragment_container, BrowseRecipesFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.browse_recipes_title))
                    transaction.commit()

                }
            }
            onBackPressed()
            true
        }


    }

    /**
     * Set the title depending on the fragment
     */
    fun setTitleBar(text: String) {
        supportActionBar?.title = text
    }

    /**
     * makes sure that pressing back while the nav drawer is open will simply close it
     * instead of exiting or going back.
     */
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return when (item.itemId) {
                R.id.nav_virtual_cupboard,
                R.id.nav_your_menu,
                R.id.nav_favourites,
                R.id.nav_browse_recipes -> true
                else -> false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * A companion object to create instances of all the objects we need to populate the various
     * fragments and activities.
     */
    companion object {
        // ingredient objects
        var vodka = Alcohol("Vodka")
        var gin = Alcohol("Gin")
        var bourbon = Alcohol("Bourbon")
        var blueCuracao = Alcohol("Blue Curacao")
        var whiteRum = Alcohol("White rum")

        var lemon = Garnish("Lemon")
        var orange = Garnish("Orange")
        var lime = Garnish("Lime")
        var maraschinoCherry = Garnish("Maraschino cherries")
        var maraschinoSyrup = Garnish("Maraschino cherry syrup")
        var pineapple = Garnish("Pineapple")
        var mint = Garnish("Mint")
        var granulatedSugar = Garnish("Granulated Sugar")

        var cola = Mixer("Cola")
        var lemonJuice = Mixer("Lemon juice")
        var orangeJuice = Mixer("Orange juice")
        var limeJuice = Mixer("Lime juice")
        var sodaWater = Mixer("Soda water")
        var pineappleJuice = Mixer("Pineapple juice")
        var coconutCream = Mixer("Coconut cream")

        // Equipment objects
        var shaker = Equipment("Shaker", R.drawable.shaker_silhoutte)
        val blender = Equipment("Blender", R.drawable.blender_silhouette)

        // recipe objects
        var bluLagIngredients = mutableMapOf(
                blueCuracao to 100,
                vodka to 100,
                lemon to -13,
                orange to -13,
                lime to -13,
                maraschinoCherry to -108,
                maraschinoSyrup to -11,
                sodaWater to 200
        )
        var bluLagInstructions = mutableListOf(
                "Put the ice cubes in a large jug. Pour over the curaçao, vodka, fruit juices and " +
                        "1 tsp syrup from the cherries. Stir until the outside of the glass feels cold.",
                "Half-fill four hurricane glasses with crushed ice, then strain in the cocktail.",
                "Top up the glasses with the soda water and gently stir, " +
                        "then garnish with the maraschino cherries."
        )
        var bluLagEquipment = mutableListOf(shaker)

        var blueLagoon = CocktailRecipe(
                "Blue Lagoon",
                R.drawable.the_blue_lagoon_cocktail,
                "A refreshing take on the classic blue lagoon.",
                "A refreshing take on the classic blue lagoon with lemonade and " +
                        "fresh citrus juice. This boozy retro classic cocktail is perfect for parties.",
                bluLagEquipment,
                bluLagIngredients,
                bluLagInstructions
        )

        var pinaColadaIngredients = mutableMapOf(
                whiteRum to 60,
                coconutCream to 60,
                pineappleJuice to 120,
                pineapple to -3
        )

        var pinaColadaInstructions = mutableListOf(
                "Pulse all the ingredients along with a handful of ice in a blender until " +
                        "smooth. Pour into a tall glass and garnish as you like."
        )

        var pinaColadaEquipment = mutableListOf(blender)

        var pinaColada = CocktailRecipe(
                "Pina Colada",
                R.drawable.pina_colada,
                "A tangy blend of coconut, pineapple and rum",
                "A tropical blend of rich coconut cream, white rum and tangy " +
                        "pineapple – serve with an umbrella for kitsch appeal.",
                pinaColadaEquipment,
                pinaColadaIngredients,
                pinaColadaInstructions
        )

        var mojitoIngredients = mutableMapOf(
                limeJuice to -13,
                whiteRum to 60,
                mint to -14,
                sodaWater to 120,
                granulatedSugar to -11
        )

        var mojitoInstructions = mutableListOf(
                "Muddle the lime juice, sugar and mint leaves in a small jug," +
                        "crushing the mint as you go – you can use the end of " +
                        "a rolling pin for this. Pour into a tall glass and " +
                        "add a handful of ice. Pour over the rum, stirring " +
                        "with a long-handled spoon. Top up with soda water, " +
                        "garnish with mint and serve."
        )

        var mojitoEquipment = mutableListOf(shaker)

        var mojito = CocktailRecipe(
                "Mojito",
                R.drawable.mojito_drink,
                "A refreshing mix of mint, lime and rum.",
                "Mix this classic cocktail for a party using fresh mint, " +
                        "white rum, sugar, zesty lime and cooling soda water. Play " +
                        "with the quantities to suit your taste.",
                mojitoEquipment,
                mojitoIngredients,
                mojitoInstructions
        )

        fun addCocktailsToList() {
            CocktailRecipe.cocktailList.add(blueLagoon)
            CocktailRecipe.cocktailList.add(pinaColada)
            CocktailRecipe.cocktailList.add(mojito)
        }

        fun addIngredientsToList() {
            Ingredient.ingredientList.add(vodka)
            Ingredient.ingredientList.add(gin)
            Ingredient.ingredientList.add(bourbon)
            Ingredient.ingredientList.add(blueCuracao)
            Ingredient.ingredientList.add(whiteRum)

            Ingredient.ingredientList.add(lemon)
            Ingredient.ingredientList.add(orange)
            Ingredient.ingredientList.add(lime)
            Ingredient.ingredientList.add(maraschinoCherry)
            Ingredient.ingredientList.add(maraschinoSyrup)
            Ingredient.ingredientList.add(pineapple)
            Ingredient.ingredientList.add(mint)
            Ingredient.ingredientList.add(granulatedSugar)

            Ingredient.ingredientList.add(cola)
            Ingredient.ingredientList.add(lemonJuice)
            Ingredient.ingredientList.add(orangeJuice)
            Ingredient.ingredientList.add(limeJuice)
            Ingredient.ingredientList.add(sodaWater)
            Ingredient.ingredientList.add(pineappleJuice)
            Ingredient.ingredientList.add(coconutCream)
        }

        fun addEquipmentToList() {
            Equipment.equipmentList.add(shaker)
            Equipment.equipmentList.add(blender)
        }

    }



}