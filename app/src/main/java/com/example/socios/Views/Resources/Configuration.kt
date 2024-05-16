package com.example.socios.Views.Resources

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.socios.Components.MainButton
import com.example.socios.Components.MyBottomAppBar
import com.example.socios.Components.MyTopAppBar
import com.example.socios.Components.Space
import com.example.socios.Components.TitleView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConfigurationView(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        bottomBar = {
            MyBottomAppBar(navController)
        }
    ) {
        ConfigurationContentView(navController)
    }
}


@Composable
fun ConfigurationContentView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleView(name = "Configuracion")
        Space()
        MainButton(name = "Cerrar sesion", backColor = Color.Red, color = Color.White) {
            navController.navigate("Login")
        }
    }
}
