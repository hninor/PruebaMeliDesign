package com.hninor.pruebamelidesign.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.pruebamelidesign.domain.model.ThemeColor
import com.hninor.pruebamelidesign.domain.usecase.ThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {
    
    val currentTheme = themeUseCase.getCurrentTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeColor.BLUE
        )
    
    fun setTheme(themeColor: ThemeColor) {
        viewModelScope.launch {
            themeUseCase.setTheme(themeColor)
        }
    }
} 