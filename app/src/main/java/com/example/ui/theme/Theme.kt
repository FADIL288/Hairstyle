package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldPrimaryLight,
    onPrimary = Color(0xFF261A00),
    primaryContainer = GoldPrimaryContainer,
    onPrimaryContainer = OnGoldPrimaryContainer,
    secondary = SlateSecondary,
    onSecondary = Color(0xFF0F172A),
    secondaryContainer = SlateSecondaryContainer,
    onSecondaryContainer = OnSlateSecondaryContainer,
    tertiary = CopperTertiary,
    onTertiary = Color(0xFF38150D),
    tertiaryContainer = CopperTertiaryContainer,
    onTertiaryContainer = OnCopperTertiaryContainer,
    background = NeutralDarkBackground,
    onBackground = NeutralDarkTextPrimary,
    surface = NeutralDarkSurface,
    onSurface = NeutralDarkTextPrimary,
    surfaceVariant = NeutralDarkSurfaceVariant,
    onSurfaceVariant = NeutralDarkTextSecondary,
    error = CoralError,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = GoldPrimaryDark,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDF9E),
    onPrimaryContainer = Color(0xFF261A00),
    secondary = Color(0xFF4A5A73),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDCE3F2),
    onSecondaryContainer = Color(0xFF0F172A),
    tertiary = Color(0xFFB05342),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFDAD4),
    onTertiaryContainer = Color(0xFF3D211B),
    background = NeutralLightBackground,
    onBackground = NeutralLightTextPrimary,
    surface = NeutralLightSurface,
    onSurface = NeutralLightTextPrimary,
    surfaceVariant = NeutralLightSurfaceVariant,
    onSurfaceVariant = NeutralLightTextSecondary,
    error = CoralError,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our bespoke salon gold theme by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
