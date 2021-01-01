package com.faranjit.clean.view

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.faranjit.clean.di.module.GlideApp
import com.faranjit.clean.domain.spotify.model.SpotifyImage

/**
 * Created by Bulent Turkmen on 1.04.2020.
 */
@BindingAdapter("url")
fun setImageUrl(view: AppCompatImageView, url: String) {
    GlideApp.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("playlistImage")
fun setPlaylistImage(view: AppCompatImageView, images: List<SpotifyImage>) {
    if (images.isNotEmpty()) {
        val playlistImage = images[0]
        GlideApp.with(view.context)
            .load(playlistImage.url)
            .override(playlistImage.width ?: -1, playlistImage.height ?: -1)
            .into(view)
    }
}