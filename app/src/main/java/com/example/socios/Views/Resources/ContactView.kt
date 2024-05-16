package com.example.socios.Views.Resources

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.CustomIcon
import com.example.socios.Components.CustomImage
import com.example.socios.Components.CustomTextBox
import com.example.socios.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactView(navController: NavController) {
    ContentContactView(navController)
}

@Composable
fun ContentContactView(navController: NavController) {
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
            Text(text = "Contactanos", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row( //Row de contacto
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImage(
                    painter = painterResource(id = R.drawable.fono),
                    contentDescription = "Creditos logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    CustomTextBox(
                        text = "+56 9 7750 0709",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Disponible 24 hrs",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Para emergencias",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
            Row( //Row de contacto
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImage(
                    painter = painterResource(id = R.drawable.fono),
                    contentDescription = "Creditos logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    CustomTextBox(
                        text = "+56 9 6788 1792",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Disponible 24 hrs",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Para ventas",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
            Row( //Row de contacto
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomImage(
                    painter = painterResource(id = R.drawable.fono),
                    contentDescription = "Creditos logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(60.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    CustomTextBox(
                        text = "+56 9 6828 0986",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Disponible 24 hrs",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                    CustomTextBox(
                        text = "Para reclamos",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun ContactViewPreview() {
    val navController = rememberNavController()
    ContactView(navController)
}