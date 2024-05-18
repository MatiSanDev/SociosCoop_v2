package com.example.socios.Views.Main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.CustomIcon
import com.example.socios.Components.CustomImage
import com.example.socios.Components.CustomTextBox
import com.example.socios.Components.MyBottomAppBar
import com.example.socios.Components.MyTopAppBar
import com.example.socios.Components.TitleView
import com.example.socios.R
import com.example.socios.Views.Logins.MainViewModel
import com.example.socios.modelo.Producto
import retrofit2.Response
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServicesView(navController: NavController, mainViewModel: MainViewModel) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        bottomBar = {
            MyBottomAppBar(navController)
        }
    ) {
        ContentServicesView(navController, mainViewModel)
    }
}
@Composable
fun ContentServicesView(navController: NavController, mainViewModel: MainViewModel) {
    var codigo by remember { mutableStateOf(TextFieldValue("")) }
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var descripcion by remember { mutableStateOf(TextFieldValue("")) }
    var precio by remember { mutableStateOf(TextFieldValue("")) }
    var mail by remember { mutableStateOf(TextFieldValue("")) }
    var mensaje by remember { mutableStateOf("") }

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 73.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = codigo,
                    onValueChange = { codigo = it },
                    label = { Text("Código") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = mail,
                    onValueChange = { mail = it },
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (codigo.text.isEmpty() || nombre.text.isEmpty() || descripcion.text.isEmpty() || precio.text.isEmpty() || mail.text.isEmpty()) {
                        mensaje = "Por favor, complete todos los campos."
                    } else {
                        val producto = Producto(
                            codigo = codigo.text,
                            nombre = nombre.text,
                            descripcion = descripcion.text,
                            precio = precio.text.toDoubleOrNull() ?: 0.0,
                            mail = mail.text
                        )
                        mainViewModel.crearProducto(producto) { response ->
                            if (response.isSuccessful) {
                                mensaje = "Producto creado exitosamente"
                                println("MSV Producto Mensaje: $mensaje")
                            } else {
                                mensaje = "Error al crear producto: ${response.message()}"
                            }
                            println("MSV Producto Mensaje: $mensaje")
                        }
                    }
                }) {
                    Text("Crear Producto")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = mensaje, color = if (mensaje.startsWith("Error")) Color.Red else Color.Green)
            }
        }
    }
}




        /*item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row para INVERSIONES
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
                    Row(horizontalArrangement = Arrangement.Center,//Row del titulo
                        modifier = Modifier

                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp)

                    ) {
                        CustomTextBox(
                            text = "INVERSIONES",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(0.dp)
                        )


                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.peso),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "DEPÓSITO A PLAZO EN PESOS",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Taza de interés de hoy: 1,25%",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )

                            }
                        }
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.uf),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "DEPÓSITO A PLAZO EN UF",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Valor UF hoy: 37.172,84 Peso",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )

                            }
                        }
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.usd),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "DEPÓSITO A PLAZO EN DÓLARES",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "1 USD hoy: $964,16",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
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
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row para AHORROS
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
                    Row(horizontalArrangement = Arrangement.Center,//Row del titulo
                        modifier = Modifier

                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { /*TODO*/}
                            .padding(10.dp)

                    ) {
                        CustomTextBox(
                            text = "AHORROS",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.vivienda),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "CUENTA AHORRO VIVIENDA",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Ahorra para tu nuevo hogar con nosotros.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )

                            }
                        }
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.uf),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "CUENTA DE AHORRO EN UF",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Asegura tu dinero según el valor de UF.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )

                            }
                        }
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.cuenta),
                            contentDescription = "Inversiones logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "CUENTA DE AHORRO PREMIUM",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Permite transacciones más rápidas.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
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
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Row(modifier = Modifier.padding(horizontal = 10.dp)) { //Row para AHORROS
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
                    Row(horizontalArrangement = Arrangement.Center,//Row del titulo
                        modifier = Modifier

                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { /*TODO*/}
                            .padding(10.dp)

                    ) {
                        CustomTextBox(
                            text = "CRÉDITOS",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                    Row( //Row de producto
                        modifier = Modifier
                            .clickable { /*TODO*/ }
                            .fillMaxWidth()
                            .padding(10.dp)

                    ) {
                        CustomImage(
                            painter = painterResource(id = R.drawable.creditos),
                            contentDescription = "credito logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(60.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            CustomTextBox(
                                text = "SOLICITA TU CRÉDITO",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            CustomTextBox(
                                text = "Necesitas un préstamo? Nosotros te ayudamos.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Thin,
                                fontFamily = FontFamily.Default,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(0.dp)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CustomIcon(Icons.Default.Call,"Contactanos",tint = Color.Red, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                CustomTextBox(
                                    text = "Contrata con nosotros",
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
fun ServicesViewPreview() {
    val navController = rememberNavController()
    ServicesView(navController)
}*/