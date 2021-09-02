package de.janniskilian.unsplashdemo.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
object UnsplashDemoColors {

    val pink = Color(0xFFE5005A)
    val red = Color(0xFFD1121C)
    val orange = Color(0xFFE84620)
    val yellow = Color(0xFFFCBF00)
    val green = Color(0xFF9BC31C)
    val seafoam = Color(0xFF8FDAB6)
    val blue = Color(0xFF00A4E0)
    val blueDark = Color(0xFF0D81B3)
    val white = Color(0xFFFFFFFF)
    val whiteDirty = Color(0xFFF8FAFA)
    val grayLight = Color(0xFFCCCCCC)
    val gray = Color(0xFF888888)
    val grayDark = Color(0xFF575A5F)
    val black = Color(0xFF000000)

    val primary = blue
    val primaryVariant = blueDark
    val secondary = primary
    val scondaryVariant = primaryVariant

    @Composable
    fun lightColors() = lightColors(
        primary = primary,
        primaryVariant = primaryVariant,
        secondary = secondary,
        secondaryVariant = primaryVariant,
        onPrimary = white,
        onSecondary = white
    )

    @Composable
    fun darkColors() = darkColors(
        primary = primary,
        primaryVariant = primaryVariant,
        secondary = secondary,
        secondaryVariant = primaryVariant,
        onPrimary = white,
        onSecondary = white
    )
}
