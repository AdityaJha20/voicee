package com.voicee.core.designsystem.theme

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

private val LightColors = lightColorScheme(
    primary = Color(0xFF3D5A80),
    secondary = Color(0xFF6B7F99),
    tertiary = Color(0xFFB5651D),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA9C7F0),
    secondary = Color(0xFFBCC7D9),
    tertiary = Color(0xFFFFB77C),
)

/**
 * The app-wide theme. Uses the phone's wallpaper colours (Material You) on
 * Android 12+, and falls back to our own palette elsewhere.
 */
@Composable
fun VoiceeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(colorScheme = colorScheme, content = content)
}
