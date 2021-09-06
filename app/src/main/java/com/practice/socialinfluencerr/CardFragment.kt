package com.practice.socialinfluencerr

import androidx.cardview.widget.CardView
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class CardFragment : Fragment() {

    var cardView: CardView ?= null
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        cardView = view.findViewById(R.id.cardView)
        cardView!!.maxCardElevation = cardView!!.cardElevation * CardAdapter.MAX_ELEVATION_FACTOR
        return view
    }
}