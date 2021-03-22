package com.k2eb.cocktailcurious
import android.app.Application
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Startup : Application() {
	override fun onCreate() {
		super.onCreate()


		// TODO: make splash screen

		val database = MockDatabase()
		database.addEquipment(Equipment("Shaker", R.drawable.ic_baseline_local_drink_24))




//		Toast.makeText(this, "after splash screen", Toast.LENGTH_LONG).show()

		// create objects for database etc.


	}
}