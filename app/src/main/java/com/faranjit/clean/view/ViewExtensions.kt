package com.faranjit.clean.view

import androidx.appcompat.widget.AppCompatImageView
import com.faranjit.clean.di.module.GlideApp

/**
 * Created by Bulent Turkmen on 5.04.2020.
 */
fun AppCompatImageView.setImageUrl(url: String) {
    GlideApp.with(this)
        .load(url)
        .into(this)
}