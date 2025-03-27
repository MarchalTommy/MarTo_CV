package com.example.martocv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.martocv.ui.navigation.AppNavigation
import com.example.martocv.ui.theme.MarToCVTheme

class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = "#801b1b1b".toColorInt(),
                darkScrim = "#801b1b1b".toColorInt()
            )
        )

        setContent {
            MarToCVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Blue
                ) {
                    AppNavigation()
                }
            }
        }
    }
}