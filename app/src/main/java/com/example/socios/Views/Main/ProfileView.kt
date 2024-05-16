package com.example.socios.Views.Main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.CustomTextBox
import com.example.socios.Components.MainButton
import com.example.socios.Components.MyBottomAppBar
import com.example.socios.Components.MyTopAppBar
import com.example.socios.Components.Space
import com.example.socios.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileView(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        bottomBar = {
            MyBottomAppBar(navController)
        }
    ) {
        ContentProfileView(navController)
    }
}


@Composable
fun ContentProfileView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.victor),
                contentDescription = "Profile image",
                Modifier
                    .clip(shape = CircleShape)
                    .size(150.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTextBox(//Nombre usuario
                    text = "VICTOR FABIÁN GÓMEZ CARU",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Space()
                CustomTextBox(
                    text = "Rut: 12.123.123-K",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                //Contenido
                CustomTextBox(
                    text = "Registrado desde: 30/04/2005",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                CustomTextBox(
                    text = "Correo: vic.gomez@gmail.com",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                CustomTextBox(
                    text = "Teléfono: +56 9 123 123 12",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                CustomTextBox(
                    text = "Dirección: Cerro Cruz 123, Buín",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                CustomTextBox(
                    text = "Cantidad de productos SociosCoop: 7",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Space()
                MainButton(name = "Editar perfil", backColor = Color.Red, color = Color.White) {
                    navController.navigate("Configuration")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileViewPreview() {
    val navController = rememberNavController()
    ProfileView(navController)
}