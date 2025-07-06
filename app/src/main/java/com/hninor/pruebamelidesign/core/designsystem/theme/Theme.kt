package com.hninor.pruebamelidesign.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.hninor.pruebamelidesign.domain.model.ThemeColor
import com.hninor.pruebamelidesign.ui.theme.ThemeViewModel

@Composable
fun MeliTheme(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val currentTheme by themeViewModel.currentTheme.collectAsState(initial = ThemeColor.BLUE)
    
    val colorScheme = when (currentTheme) {
        ThemeColor.BLUE -> lightColorScheme(
            primary = PrimaryBlue,
            onPrimary = OnPrimary,
            surface = Surface,
            onSurface = OnSurface,
            background = Background,
            onBackground = OnBackground,
            error = Error,
            onError = OnError,
            surfaceVariant = SurfaceVariant,
            onSurfaceVariant = OnSurfaceVariant,
            outline = Outline,
            outlineVariant = OutlineVariant
        )
        ThemeColor.ORANGE -> lightColorScheme(
            primary = PrimaryOrange,
            onPrimary = OnPrimary,
            surface = Surface,
            onSurface = OnSurface,
            background = Background,
            onBackground = OnBackground,
            error = Error,
            onError = OnError,
            surfaceVariant = SurfaceVariant,
            onSurfaceVariant = OnSurfaceVariant,
            outline = Outline,
            outlineVariant = OutlineVariant
        )
        ThemeColor.VIOLET -> lightColorScheme(
            primary = PrimaryViolet,
            onPrimary = OnPrimary,
            surface = Surface,
            onSurface = OnSurface,
            background = Background,
            onBackground = OnBackground,
            error = Error,
            onError = OnError,
            surfaceVariant = SurfaceVariant,
            onSurfaceVariant = OnSurfaceVariant,
            outline = Outline,
            outlineVariant = OutlineVariant
        )
        ThemeColor.GREEN -> lightColorScheme(
            primary = PrimaryGreen,
            onPrimary = OnPrimary,
            surface = Surface,
            onSurface = OnSurface,
            background = Background,
            onBackground = OnBackground,
            error = Error,
            onError = OnError,
            surfaceVariant = SurfaceVariant,
            onSurfaceVariant = OnSurfaceVariant,
            outline = Outline,
            outlineVariant = OutlineVariant
        )
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
} 