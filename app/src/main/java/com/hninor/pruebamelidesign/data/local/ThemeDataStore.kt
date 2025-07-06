package com.hninor.pruebamelidesign.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hninor.pruebamelidesign.domain.model.ThemeColor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemeDataStore @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val themeKey = intPreferencesKey("theme_color")
    
    fun getTheme(): Flow<ThemeColor> {
        return context.dataStore.data.map { preferences ->
            val themeOrdinal = preferences[themeKey] ?: ThemeColor.BLUE.ordinal
            ThemeColor.values()[themeOrdinal]
        }
    }
    
    suspend fun setTheme(themeColor: ThemeColor) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = themeColor.ordinal
        }
    }
} 