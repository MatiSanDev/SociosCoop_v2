package com.example.socios.Views.Resources

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.socios.Components.MainButton
import com.example.socios.Components.MyBottomAppBar
import com.example.socios.Components.MyTopAppBar
import com.example.socios.Components.Space
import com.example.socios.Components.TitleView
import com.example.socios.R
import com.example.socios.Views.Main.HomeView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchView(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        bottomBar = {
            MyBottomAppBar(navController)
        }
    ) {
        SearchContentView(navController)
    }
}


@Composable
fun SearchContentView(navController: NavController) {
    var client by remember {
        mutableStateOf("")
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 73.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        item {
            CustomTextBox(
                text = "Encuentra datos de clientes: ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(0.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = client,
                    onValueChange = { client = it },
                    singleLine = true,
                    placeholder = {
                        CustomTextBox(
                            text = "Buscar cliente",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(0.dp)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                )
            }
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row cliente
                Column(//Columna para separar entre titulo y contenido
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.victor),
                            contentDescription = "profile image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(shape = CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "VICTOR FABIÁN GOMEZ CARU",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cliente ID: 1234",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Rut: 12.123.123-K",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Cantidad de productos SociosCoop: 7",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactar",tint = Color.Cyan, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contactar",
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
        }
    }
}

@Preview
@Composable
fun SearchViewPreview() {
    val navController = rememberNavController()
    SearchView(navController)
}