package com.faranjit.clean.di.module

import com.faranjit.clean.core.ApplicationConfig
import com.faranjit.clean.core.StorageHelper
import com.faranjit.clean.di.ApplicationScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Bulent Turkmen on 31.03.2020.
 */
@Module
class NetworkModule {

    companion object {
        const val SPOTIFY_BASE_URL = "https://api.spotify.com/v1/"
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpForGlide(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .build()

    @ApplicationScope
    @Provides
    internal fun provideHttpLoggingInterceptor(applicationConfig: ApplicationConfig): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (applicationConfig.isLogEnabled()) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @ApplicationScope
    @Provides
    fun provideSpotifyRetrofit(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        storageHelper: StorageHelper
    ): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(SPOTIFY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(
                        Interceptor { chain ->
                            val request = chain.request().newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Bearer " + storageHelper.spotifyAccessToken
                                )
                                .build()
                            chain.proceed(request)
                        }
                    )
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }
}