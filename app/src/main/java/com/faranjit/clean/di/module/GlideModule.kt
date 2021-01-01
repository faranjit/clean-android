package com.faranjit.clean.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.faranjit.clean.SpotifyApplication
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
@GlideModule
class GlideModule : AppGlideModule() {

    lateinit var okHttpClient: OkHttpClient

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context)
            .setMemoryCacheScreens(2f)
            .build()

        builder
            .setMemoryCache(LruResourceCache(calculator.memoryCacheSize.toLong()))
            .setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_RGB_565))
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        okHttpClient =
            (context.applicationContext as SpotifyApplication).applicationComponent.getOkHttpForGlide()
        val factory = OkHttpUrlLoader.Factory(okHttpClient)
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, factory)
    }
}