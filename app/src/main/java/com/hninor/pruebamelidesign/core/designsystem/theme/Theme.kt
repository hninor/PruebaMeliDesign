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
            primary = PrimaryYellow,
            secondary = PrimaryBlue,
            secondaryContainer = SecondaryBlueContainer,
            tertiary = TertiaryML,
            tertiaryContainer = TertiaryMLContainer,
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
            primary = PrimaryYellow,
            secondary = PrimaryOrange,
            secondaryContainer = SecondaryOrangeContainer,
            tertiary = TertiaryML,
            tertiaryContainer = TertiaryMLContainer,
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
            primary = PrimaryYellow,
            secondary = PrimaryViolet,
            secondaryContainer = SecondaryVioletContainer,
            tertiary = TertiaryML,
            tertiaryContainer = TertiaryMLContainer,
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
            primary = PrimaryYellow,
            secondary = PrimaryGreen,
            secondaryContainer = SecondaryGreenContainer,
            tertiary = TertiaryML,
            tertiaryContainer = TertiaryMLContainer,
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