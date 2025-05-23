package com.first.miniproject.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFDADB),
    onPrimary = Color(0xFF690000),
    primaryContainer = Color(0xFF93000A),
    onPrimaryContainer = Color(0xFFFFDAD6),
    secondary = Color(0xFFCCC5C5),
    onSecondary = Color(0xFF332F2F),
    secondaryContainer = Color(0xFF4A4545),
    onSecondaryContainer = Color(0xFFE8E0E0),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFFFD9E3),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF1E1E1E),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    inverseSurface = Color(0xFFE6E1E5),
    inverseOnSurface = Color(0xFF1C1B1F),
    inversePrimary = Color(0xFFBA1A1A)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8A0303),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFDADB),
    onPrimaryContainer = Color(0xFF690000),
    secondary = Color(0xFFCCC5C5),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFFE8E0E0),
    onSecondaryContainer = Color(0xFF1C1B1F),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFFFFD9E3),
    onTertiaryContainer = Color(0xFF1C1B1F),
    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFCD8DF),
    onErrorContainer = Color(0xFF370617),
    background = Color(0xFFF3F3F3),
    onBackground = Color(0xFF1C1C1C),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF111111),
    surfaceVariant = Color(0xFFF1EFEF),
    onSurfaceVariant = Color(0xFF4D4D4D),
    outline = Color(0xFF8F8F8F),
    outlineVariant = Color(0xFFCFCFCF),
    inverseSurface = Color(0xFF2D2D2D),
    inverseOnSurface = Color(0xFFFAFAFA),
    inversePrimary = Color(0xFFBA1A1A)
)


/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/


@Composable
fun MiniProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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