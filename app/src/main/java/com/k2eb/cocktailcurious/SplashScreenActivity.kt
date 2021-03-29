package com.k2eb.cocktailcurious

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
	private val timer:Long = 5000
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.splash_screen)

		val progressBar1 = findViewById<ProgressBar>(R.id.progressBar);

		/**
		 * Once 5 seconds have passed, the main activity is started
		 */
		Handler().postDelayed({
			val intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
			finish()
		}, timer)

//		Toast.makeText(this, "splash screen", Toast.LENGTH_LONG).show()



	}
}