package com.hninor.pruebamelidesign.domain.usecase

import com.hninor.pruebamelidesign.domain.model.ThemeColor
import com.hninor.pruebamelidesign.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {
    fun getCurrentTheme(): Flow<ThemeColor> {
        return themeRepository.getCurrentTheme()
    }
    
    suspend fun setTheme(themeColor: ThemeColor) {
        themeRepository.setTheme(themeColor)
    }
} 