package com.k2eb.cocktailcurious

import android.graphics.drawable.Icon
import android.media.audiofx.DynamicsProcessing

class Equipment(var name: String, var iconId: Int) {

	companion object {
		val equipmentList = mutableListOf<Equipment>()
	}
}