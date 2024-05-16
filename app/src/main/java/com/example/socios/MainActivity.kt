package com.example.socios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.socios.Screens.NavManager
import com.example.socios.ui.theme.SociosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SociosTheme {
                NavManager()
            }
        }
    }
}
