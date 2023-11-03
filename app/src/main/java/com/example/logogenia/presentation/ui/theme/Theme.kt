package com.example.logogenia.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val DarkColorPalette = darkColorScheme(
    primary = Blue900,
    onPrimary = Yellow20,
    primaryContainer = Yellow30,
    onPrimaryContainer = Yellow90,
    inversePrimary = Yellow40,
    secondary = DarkYellow80,
    onSecondary = DarkYellow20,
    secondaryContainer = DarkYellow30,
    onSecondaryContainer = DarkYellow90,
    tertiary = Orange80,
    onTertiary = Color.White,
    tertiaryContainer = Orange30,
    onTertiaryContainer = Orange90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Gray95,
    onBackground = Gray90,
    surface = Gray10,
    onSurface = Gray90,
    inverseSurface = Gray90,
    inverseOnSurface = Gray10,
    surfaceTint = Gray95,
)

private val LightColorPalette = lightColorScheme(
    primary = Blue700,
    onPrimary = Gray10,
    primaryContainer = Yellow90,
    onPrimaryContainer = Yellow10,
    inversePrimary = Yellow80,
    secondary = DarkYellow40,
    onSecondary = Color.White,
    secondaryContainer = DarkYellow90,
    onSecondaryContainer = DarkYellow10,
    tertiary = Orange40,
    onTertiary = Color.White,
    tertiaryContainer = Orange90,
    onTertiaryContainer = Orange10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Gray95,
    onBackground = Gray10,
    surface = Gray90,
    onSurface = Gray30,
    inverseSurface = Gray20,
    inverseOnSurface = Gray95,
    surfaceTint = Gray95,
)

@Composable
fun LogogeniaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
       /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}