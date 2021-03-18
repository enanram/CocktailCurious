package com.k2eb.cocktailcurious
import android.app.Application
import android.graphics.drawable.Icon
import android.widget.Toast


class Startup : Application() {
	override fun onCreate() {
		super.onCreate()

		// TODO: make splash screen

		val database = MockDatabase()
		database.addEquipment(Equipment("Shaker", R.drawable.ic_baseline_local_drink_24))


		Thread.sleep(5000)

		Toast.makeText(this, "test startup again", Toast.LENGTH_LONG).show()


		// create objects for database etc.


	}
}