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
}