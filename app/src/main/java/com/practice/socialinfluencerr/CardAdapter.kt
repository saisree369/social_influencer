package com.practice.socialinfluencerr


import androidx.cardview.widget.CardView

interface CardAdapter {

    companion object {

        val MAX_ELEVATION_FACTOR = 8
    }

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView?

    fun getCount(): Int

}