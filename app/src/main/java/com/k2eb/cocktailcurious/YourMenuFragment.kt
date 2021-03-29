package com.k2eb.cocktailcurious

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class YourMenuFragment : Fragment() {

    lateinit var recipeRecycler: RecyclerView
    private var menuList = arrayListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val flater = inflater.inflate(R.layout.fragment_your_menu, container, false)
        recipeRecycler = flater.findViewById(R.id.yourmenu_results_recycler)
        recipeRecycler.layoutManager = LinearLayoutManager(recipeRecycler.context)

        // get data function

        return flater
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                VirtualCupboardFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

}