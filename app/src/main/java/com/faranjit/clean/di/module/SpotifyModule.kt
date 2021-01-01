package com.faranjit.clean.di.module

import com.faranjit.clean.data.RequestExecutor
import com.faranjit.clean.data.spotify.datasource.SpotifyRemoteDataSource
import com.faranjit.clean.data.spotify.repository.SpotifyDataRepository
import com.faranjit.clean.domain.Executor
import com.faranjit.clean.domain.spotify.remote.SpotifyApi
import com.faranjit.clean.domain.spotify.repository.SpotifyRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
@Module
class SpotifyModule {

    @Provides
    fun provideRequestExecutor(): Executor = RequestExecutor()

    @Provides
    fun provideSpotifyRemoteDataSource(
        retrofit: Retrofit,
        executor: Executor
    ): SpotifyRemoteDataSource =
        SpotifyRemoteDataSource(retrofit.create(SpotifyApi::class.java), executor)

    @Provides
    fun provideSpotifyRepository(spotifyRemoteDataSource: SpotifyRemoteDataSource): SpotifyRepository =
        SpotifyDataRepository(spotifyRemoteDataSource)
}