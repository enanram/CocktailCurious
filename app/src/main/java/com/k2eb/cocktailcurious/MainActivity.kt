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
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, VirtualCupboardFragment()).commit()
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
                    var transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, VirtualCupboardFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.virtual_cupboard_title))
                    transaction.commit()

                }
                R.id.nav_your_menu -> {
//                    Toast.makeText(applicationContext, "clicked your menu", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, YourMenuFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.your_menu_title))
                    transaction.commit()

                }
                R.id.nav_favourites -> {
//                    Toast.makeText(applicationContext, "clicked favourites", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FavouritesFragment())
                    transaction.addToBackStack(null)
                    setTitleBar(getString(R.string.favourites_title))
                    transaction.commit()

                }
                R.id.nav_browse_recipes -> {
//                    Toast.makeText(applicationContext, "clicked browse recipes", Toast.LENGTH_LONG).show()
                    var transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, BrowseRecipesFragment())
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


        var cupboardList = mutableListOf<Ingredient>()

        // ingredient objects
        var vodka = Alcohol("Vodka")
        var gin = Alcohol("Gin")
        var bourbon = Alcohol("Bourbon")
        var blueCuracao = Alcohol("Blue Curacao")
        var whiteRum = Alcohol("White Rum")
        var darkRum = Alcohol("Dark Rum")
        var tripleSec = Alcohol("Triple Sec")
        var cremeDeMenthe = Alcohol("Cr??me de Menthe")
        var whiteCreme = Alcohol("White Cr??me de Cacao")
        var angosturaBitters = Alcohol("Angostura bitters")

        var lemon = Garnish("Lemon")
        var orange = Garnish("Orange")
        var lime = Garnish("Lime")
        var maraschinoCherry = Garnish("Maraschino cherries")
        var maraschinoSyrup = Garnish("Maraschino cherry syrup")
        var pineapple = Garnish("Pineapple")
        var mint = Garnish("Mint")
        var sugar = Garnish("Sugar")
        var mapleSyrup = Garnish("Maple syrup")
        var strawberries = Garnish("Strawberries")
        var sugarSyrup = Garnish("Sugar syrup")
        var eggWhite = Garnish("Egg white")

        var cola = Mixer("Cola")
        var orangeJuice = Mixer("Orange juice")
        var sodaWater = Mixer("Soda water")
        var pineappleJuice = Mixer("Pineapple juice")
        var coconutCream = Mixer("Coconut cream")
        var grenadine = Mixer("Grenadine")
        var almondSyrup = Mixer("Almond syrup")
        var singleCream = Mixer("Single cream")
        var ice = Mixer("Ice")

        // Equipment objects
        var shaker = Equipment("Shaker", R.drawable.shaker_silhoutte)
        var blender = Equipment("Blender", R.drawable.blender_silhouette)
        var strainer = Equipment("Strainer", R.drawable.strainer_silhouette)

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
            "Put the ice cubes in a large jug. Pour over the cura??ao, vodka, fruit juices and " +
                    "1 tsp syrup from the cherries. Stir until the outside of the glass feels cold.",
            "Half-fill four hurricane glasses with crushed ice, then strain in the cocktail.",
            "Top up the glasses with the soda water and gently stir, " +
                    "then garnish with the maraschino cherries."
        )
        var bluLagEquipment = mutableListOf(shaker, strainer)

        var blueLagoon = CocktailRecipe(
            "Blue Lagoon",
            R.drawable.blue_lagoon,
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
                    "pineapple ??? serve with an umbrella for kitsch appeal.",
            pinaColadaEquipment,
            pinaColadaIngredients,
            pinaColadaInstructions
        )

        var mojitoIngredients = mutableMapOf(
            lime to -13,
            whiteRum to 60,
            mint to -14,
            sodaWater to 120,
            sugar to -11
        )

        var mojitoInstructions = mutableListOf(
            "Muddle the lime juice, sugar and mint leaves in a small jug," +
                    "crushing the mint as you go ??? you can use the end of " +
                    "a rolling pin for this.",
            "Pour into a tall glass and add a handful of ice. Pour over the rum, stirring " +
                    "with a long-handled spoon.",
            "Top up with soda water, garnish with mint and serve."
        )

        var mojitoEquipment = mutableListOf(shaker, strainer)

        var mojito = CocktailRecipe(
            "Mojito",
            R.drawable.mojito,
            "A refreshing mix of mint, lime and rum.",
            "Mix this classic cocktail for a party using fresh mint, " +
                    "white rum, sugar, zesty lime and cooling soda water. Play " +
                    "with the quantities to suit your taste.",
            mojitoEquipment,
            mojitoIngredients,
            mojitoInstructions
        )

        var maiTaiIngredients = mutableMapOf(
            whiteRum to -17,
            darkRum to -17,
            tripleSec to -17,
            grenadine to -16,
            almondSyrup to -16,
            lime to -15,
            maraschinoCherry to -101
        )

        var maiTaiInstructions = mutableListOf(
            "Stir all the ingredients together in a jug or shake them in a cocktail " +
                    "shaker. Put a few cubes of ice in a tumbler, pour over the " +
                    "liquid and garnish with a cherry."
        )

        var maiTaiEquipment = mutableListOf(shaker, strainer)

        var maiTai = CocktailRecipe(
            "Mai Tai",
            R.drawable.mai_tai,
            "A strong, tart cocktail, with a prominent rum flavor.",
            "Mix this classic cocktail for a party using fresh mint, white rum, " +
                    "sugar, zesty lime and cooling soda water. Play with the quantities to " +
                    "suit your taste.",
            maiTaiEquipment,
            maiTaiIngredients,
            maiTaiInstructions
        )

        var grasshopperIngredients = mutableMapOf(
            cremeDeMenthe to 25,
            whiteCreme to 25,
            singleCream to 25,
            mint to -7,
            ice to -2
        )

        var grasshopperInstructions = mutableListOf(
            "Start by making the garnish. Take a sprig of mint, strip off the leaves at the " +
                    "base and dip the stalk in the melted chocolate, holding the upper leaves " +
                    "gently. Leave to set on a plate or tray. Fill a cocktail shaker with ice then " +
                    "pour in the liqueurs and cream. Shake hard until the outside of the cocktail " +
                    "shaker is cold, then strain in to a cocktail glass or small coupe. Garnish " +
                    "with the chocolate-dipped sprig of mint."
        )

        var grasshopperEquipment = mutableListOf(shaker, strainer)

        var grasshopper = CocktailRecipe(
            "Grasshopper",
            R.drawable.grasshopper,
            "A sweet, mint-flavored, after-dinner drink.",
            "Looking for a spooky cocktail to serve at a Halloween party? With its bright " +
                    "green colour, this minty grasshopper cocktail will certainly look the part...",
            grasshopperEquipment,
            grasshopperIngredients,
            grasshopperInstructions,
            false
        )

        var oldFashionedIngredients = mutableMapOf(
            bourbon to 50,
            angosturaBitters to -23,
            mapleSyrup to -11,
            orange to -26,
            ice to -30
        )

        var oldFashionedInstructions = mutableListOf(
            "Add the bourbon, Angostura bitters, maple syrup and ice to a tall glass.",
            "Stir without agitating until condensation forms on the outside of the glass.",
            "Strain over fresh ice into an old fashioned glass.",
            "Squeeze the orange peel over the drink so that the oils spray out. You can light" +
                    " the oil for some extra flair. Then add the peel into the drink."
        )

        var oldFashionedEquipment = mutableListOf(strainer)

        var oldFashioned = CocktailRecipe(
            "Old fashioned",
            R.drawable.old_fashioned,
            "A complex, boozy drink, perfect for sipping.",
            "This is more than just some sweetened whiskey - it's a complex " +
                    "drink ideal for sipping at the end of the evening. " +
                    "Try to use the best bourbon you can find.",
            oldFashionedEquipment,
            oldFashionedIngredients,
            oldFashionedInstructions
        )

        // Whiskey Sour Recipe
        var whiskeySourIngredients = mutableMapOf(
            bourbon to 50,
            lemon to -15,
            sugarSyrup to 12,
            angosturaBitters to -19,
            eggWhite to -25,
            lemon to -5,
            orange to -7,
            maraschinoCherry to -101,
            ice to -30

        )
        var whiskeySourInstructions = mutableListOf(
            "Shake all of the ingredients (except for the lemon zest) hard with ice and strain " +
                    "into an ice-filled glass.",
            "Squeeze the lemon zest, shiny side down over the drink so the scented oils spray" +
                    "across the surface. ",
            "Discard the zest, add the garnish and serve."
        )

        var whiskeySourEquipment = mutableListOf(shaker, strainer)

        var whiskeySour = CocktailRecipe(
            "Whiskey Sour",
            R.drawable.whiskey_sour,
            "Perfecting this drink eludes mere mortals.",
            "A well-executed Whiskey Sour has the perfect balance of sweet and sour " +
                    "and highlights the wonderful flavours of a good whiskey. " +
                    "It has a silky smooth texture so it goes down easy and it's topped with a wonderful " +
                    "meringue. If that's not how you would describe your Whiskey Sour, you're making it " +
                    "wrong.",
            whiskeySourEquipment,
            whiskeySourIngredients,
            whiskeySourInstructions,
            false
        )

        // Strawberry Daiquiri recipe
        var strawberryDaiquiriIngredients = mutableMapOf(
            strawberries to -32,
            ice to -31,
            whiteRum to 100,
            lime to -15,
            strawberries to -24,
            lime to -7

        )
        var strawberryDaiquiriInstructions = mutableListOf(
            "Blend the strawberries then push the resulting puree through a sieve to remove " +
                    "some of the seeds. ",
            "Tip the sieved puree into the blender again and add the ice, rum and lime juice.",
            "Blend again and divide the mixture between two glasses.",
            "Place the slice of lime and strawberry halves on the side of the glass."
        )

        var strawberryDaiquiriEquipment = mutableListOf(blender)

        var strawberryDaiquiri = CocktailRecipe(
            "Strawberry Daiquiri",
            R.drawable.strawberry_daiquiri,
            "A blended cocktail served as a boozy fresh fruit smoothie.",
            "The Strawberry Daiquiri is the most popular variation of the classic " +
                    "rum daiquiri. An excellent cocktail for those warmer summer days.",
            strawberryDaiquiriEquipment,
            strawberryDaiquiriIngredients,
            strawberryDaiquiriInstructions
        )

        //Mojito Mocktail recipe
        var mojitoMocktailIngredients = mutableMapOf(
            sugar to -16,
            mint to -2,
            lime to -27,
            sodaWater to 750

        )
        var mojitoMocktailInstructions = mutableListOf(
            "Muddle the sugar with leaves from the mint with the end of a rolling pin. ",
            "Put a scoop of ice into two tall glasses and divide the lime juice equally " +
                    "along with the mint.",
            "Top up with soda."
        )

        var mojitoMocktailEquipment = mutableListOf(shaker)

        var mojitoMocktail = CocktailRecipe(
            "Mojito Mocktail",
            R.drawable.mojito_mocktail,
            "Try a refreshing minty mocktail, all the flavour none of the booze!",
            "A refreshing, non-alcoholic mojito cocktail recipe that skips the usual " +
                    "rum to create a booze-free blend.",

            mojitoMocktailEquipment,
            mojitoMocktailIngredients,
            mojitoMocktailInstructions
        )

        fun addCocktailsToList() {
            CocktailRecipe.cocktailList.add(blueLagoon)
            CocktailRecipe.cocktailList.add(pinaColada)
            CocktailRecipe.cocktailList.add(mojito)
            CocktailRecipe.cocktailList.add(maiTai)
            CocktailRecipe.cocktailList.add(grasshopper)
            CocktailRecipe.cocktailList.add(oldFashioned)
            CocktailRecipe.cocktailList.add(whiskeySour)
            CocktailRecipe.cocktailList.add(strawberryDaiquiri)
            CocktailRecipe.cocktailList.add(mojitoMocktail)
        }

        fun addIngredientsToList() {
            Ingredient.ingredientList.add(vodka)
            Ingredient.ingredientList.add(gin)
            Ingredient.ingredientList.add(bourbon)
            Ingredient.ingredientList.add(blueCuracao)
            Ingredient.ingredientList.add(whiteRum)
            Ingredient.ingredientList.add(darkRum)
            Ingredient.ingredientList.add(tripleSec)
            Ingredient.ingredientList.add(cremeDeMenthe)
            Ingredient.ingredientList.add(whiteCreme)
            Ingredient.ingredientList.add(angosturaBitters)

            Ingredient.ingredientList.add(lemon)
            Ingredient.ingredientList.add(orange)
            Ingredient.ingredientList.add(lime)
            Ingredient.ingredientList.add(maraschinoCherry)
            Ingredient.ingredientList.add(maraschinoSyrup)
            Ingredient.ingredientList.add(pineapple)
            Ingredient.ingredientList.add(mint)
            Ingredient.ingredientList.add(sugar)
            Ingredient.ingredientList.add(mapleSyrup)
            Ingredient.ingredientList.add(strawberries)

            Ingredient.ingredientList.add(cola)
            Ingredient.ingredientList.add(orangeJuice)
            Ingredient.ingredientList.add(sodaWater)
            Ingredient.ingredientList.add(pineappleJuice)
            Ingredient.ingredientList.add(coconutCream)
            Ingredient.ingredientList.add(grenadine)
            Ingredient.ingredientList.add(almondSyrup)
            Ingredient.ingredientList.add(singleCream)
            Ingredient.ingredientList.add(ice)

        }

        fun addEquipmentToList() {
            Equipment.equipmentList.add(shaker)
            Equipment.equipmentList.add(blender)
            Equipment.equipmentList.add(strainer)
        }
    }
}