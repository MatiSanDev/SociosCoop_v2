package com.example.socios.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


//Top Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            val currentRoute = getCurrentRoute(navController)
            TitleBar(name = currentRoute)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Red
        ),
        actions = {
            Row {
                MainIconButton(icon = Icons.Default.Search, "Buscar") {
                    navController.navigate("Search")
                }
                MainIconButton(icon = Icons.Default.Settings, "Configuracion") {
                    navController.navigate("Configuration")
                }
                MainIconButton(icon = Icons.Default.Notifications, "Anuncios") {
                    navController.navigate("Notifications")
                }
                MainIconButton(icon = Icons.Default.LocationOn, "Direcciones") {
                    navController.navigate("Directions")
                }
                MainIconButton(icon = Icons.Default.Call, "Contacto") {
                    navController.navigate("Contact")
                }
                MainIconButton(icon = Icons.Default.ExitToApp, "Cerrar sesion") {
                    navController.navigate("Login")
                }
            }
        }
    )
}

//Cambio nombre titulos y rutas
@Composable
fun getCurrentRoute(navController: NavController): String {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    return when (currentRoute) {
        "Home" -> "Inicio"
        "Notifications" -> "Anuncios"
        "Profile" -> "Perfil"
        "Configuration" -> "Ajustes"
        "Services" -> "Servicios"
        "Search" -> "Buscar"
        else -> ""
    }
}

//Bottom Bar
@Composable
fun MyBottomAppBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.Red
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MainIconButton(icon = Icons.Default.AccountCircle, "Perfil") {
                navController.navigate("Profile")
            }
            MainIconButton(icon = Icons.Default.Home, "Inicio") {
                navController.navigate("Home")
            }
            MainIconButton(icon = Icons.Default.Star, "Servicios") {
                navController.navigate("Services")
            }
        }
    }
}

//Titulos
@Composable
fun TitleBar(name: String) {
    Text(text = name, fontSize = 23.sp, color = Color.White)
}

//Iconos
@Composable
fun MainIconButton(icon: ImageVector, nombre: String, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
@Composable
fun MyText(
    text: String,
    style: androidx.compose.ui.text.TextStyle,
    lines: Int = Int.MAX_VALUE
) {
    Text(text = text, style = style, maxLines = lines)
}

@Composable
fun MySubText(
    text: String,
    color: Color,
    style: androidx.compose.ui.text.TextStyle,
    lines: Int = Int.MAX_VALUE
) {
    Text(text = text, color = color, style = style, maxLines = lines)
}