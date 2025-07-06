package com.hninor.pruebamelidesign.data.repository

import com.hninor.pruebamelidesign.data.local.ThemeDataStore
import com.hninor.pruebamelidesign.domain.model.ThemeColor
import com.hninor.pruebamelidesign.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val themeDataStore: ThemeDataStore
) : ThemeRepository {
    
    override fun getCurrentTheme(): Flow<ThemeColor> {
        return themeDataStore.getTheme()
    }
    
    override suspend fun setTheme(themeColor: ThemeColor) {
        themeDataStore.setTheme(themeColor)
    }
} 