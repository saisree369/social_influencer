package com.practice.socialinfluencerr

import android.graphics.Color


class CardItem{
    private var mTextResource: Int = 0
    private var mTitleResource: Int = 0
    private var mImageResource: Int =0



    fun getText(): Int {
        return mTextResource
    }

    fun getTitle(): Int {
        return mTitleResource
    }

    fun getImage():Int{
        return mImageResource
    }

    constructor(
        title: Int, text: Int, image: Int
    ) {
        this.mTextResource = title
        this.mTitleResource = text
        this.mImageResource= image

    }

}