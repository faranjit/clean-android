package com.faranjit.clean.core

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
class StorageHelper @Inject constructor(context: Context) {

    companion object {
        const val PREF_NAME = "com.faranjit.clean.prefs"

        const val PREF_KEY_SPOTIFY_ACCESS_TOKEN = "spotify_access_token"
    }

    private val sharedPreferences: SharedPreferences

    var spotifyAccessToken: String?
        get() = getString(PREF_KEY_SPOTIFY_ACCESS_TOKEN)
        set(value) = putString(PREF_KEY_SPOTIFY_ACCESS_TOKEN, value)

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String?) =
        sharedPreferences.edit().putString(key, value).apply()

    fun getString(key: String) = getString(key, null)

    fun getString(key: String, defValue: String?): String? =
        sharedPreferences.getString(key, defValue)
}