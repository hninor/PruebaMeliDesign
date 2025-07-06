package com.hninor.pruebamelidesign.domain.repository

import com.hninor.pruebamelidesign.domain.model.ThemeColor
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getCurrentTheme(): Flow<ThemeColor>
    suspend fun setTheme(themeColor: ThemeColor)
} 