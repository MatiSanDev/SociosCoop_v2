package com.example.socios.Views.Resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.socios.Components.Space
import com.example.socios.R
import com.example.socios.Views.Logins.LoginView

@Composable
fun DirectionsView(navController: NavController) {
    ContentDirectionsView(navController)
}

@Composable
fun ContentDirectionsView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Row {
            Text(text = "Encontremonos", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ubicasao),
                contentDescription = "Profile image",
                Modifier
                    .size(300.dp)
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
                CustomTextBox(
                    text = "Sucursal Providencia",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(0.dp)
                )
                Space()
                CustomTextBox(
                    text = "Ubicación:\n" + "Providencia 3227 Of 1309",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextBox(
                    text =
                    "Horario:\n" + "Lun a Jue 9:00 a 14:00 / 14:45 a 17:00\n" + "Vie 9:00 a 14:00 / 14:45 a 16:30",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun DirectionsViewPreview() {
    val navController = rememberNavController()
    DirectionsView(navController)
}