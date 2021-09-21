package com.practice.socialinfluencerr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView



    @BindingAdapter("image")
    fun loadImage(view: SimpleDraweeView, url: String) {
        view.setImageURI(url)

}