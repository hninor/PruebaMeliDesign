package com.hninor.pruebamelidesign.core.designsystem.theme

import androidx.compose.ui.graphics.Color

// Paleta oficial Mercado Libre
val YellowML = Color(0xFFFFE600)   // Amarillo Mercado Libre
val BlueML = Color(0xFF3483FA)     // Azul Mercado Libre
val LightGrayML = Color(0xFFF5F5F5)
val MediumGrayML = Color(0xFFEEEEEE)
val DarkGrayML = Color(0xFF333333)
val GreenML = Color(0xFF00A650)
val RedML = Color(0xFFF23D4F)
val VioletML = Color(0xFFA500FF)
val OrangeML = Color(0xFFFF6F00)

// Colores base
val Blue = BlueML
val Orange = OrangeML
val Violet = VioletML
val Green = GreenML
val Yellow = YellowML

// Colores del sistema
val PrimaryBlue = BlueML
val PrimaryOrange = OrangeML
val PrimaryViolet = VioletML
val PrimaryGreen = GreenML
val PrimaryYellow = YellowML

val OnPrimary = DarkGrayML // Mejor contraste con amarillo
val Surface = LightGrayML
val OnSurface = DarkGrayML
val Background = Color(0xFFFFFFFF)
val OnBackground = DarkGrayML
val Error = RedML
val OnError = Color(0xFFFFFFFF)

// Colores de estado
val SurfaceVariant = MediumGrayML
val OnSurfaceVariant = DarkGrayML
val Outline = MediumGrayML
val OutlineVariant = LightGrayML

// Colores de producto
val PriceColor = GreenML
val DiscountColor = RedML
val RatingColor = Color(0xFFFFD700) // Amarillo para estrellas 