package com.example.martocv.ui.theme

import androidx.compose.ui.graphics.Color

// Define your soft & warm palette
val Cream = Color(0xFFFFF8E1) // Light background
val SoftPeach = Color(0xFFFFE0B2) // Accent Background / Cards
val WarmSand = Color(0xFFD7CCC8) // Subtle borders or dividers
val MutedOrange = Color(0xFFFFB74D) // Primary Action / Highlights
val DeepBrown = Color(0xFF5D4037) // Primary Text
val MediumGrey = Color(0xFF757575) // Secondary Text
val DarkSurface = Color(0xFF303030) // Dark theme surface

// Consider variants for light/dark theme if needed directly
val PrimaryLight = MutedOrange
val OnPrimaryLight = DeepBrown
val PrimaryContainerLight = SoftPeach
val OnPrimaryContainerLight = DeepBrown
val SecondaryLight = WarmSand
val OnSecondaryLight = DeepBrown
val TertiaryLight = Color(0xFF81C784) // Example tertiary - adjust as needed
val OnTertiaryLight = Color.Black
val BackgroundLight = Cream
val OnBackgroundLight = DeepBrown
val SurfaceLight = Cream // Or a slightly different shade like Color(0xFFFFFDF7)
val OnSurfaceLight = DeepBrown
val SurfaceVariantLight = SoftPeach
val OnSurfaceVariantLight = DeepBrown
val OutlineLight = WarmSand

val PrimaryDark = MutedOrange
val OnPrimaryDark = DeepBrown
val PrimaryContainerDark = Color(0xFF4E342E) // Darker version of brown/orange
val OnPrimaryContainerDark = SoftPeach
val SecondaryDark = WarmSand
val OnSecondaryDark = DeepBrown
val TertiaryDark = Color(0xFF4CAF50) // Darker Green
val OnTertiaryDark = Color.White
val BackgroundDark = Color(0xFF1F1F1F) // Dark background
val OnBackgroundDark = Cream
val SurfaceDark = DarkSurface // Defined above
val OnSurfaceDark = Cream
val SurfaceVariantDark = Color(0xFF424242) // Darker grey/brown
val OnSurfaceVariantDark = WarmSand
val OutlineDark = MediumGrey