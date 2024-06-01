package com.example.socios.Views.Main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.Space
import com.example.socios.Components.maxWidthIn
import com.example.socios.R
import com.example.socios.bdd.BaseDatos
import com.example.socios.modelo.ProductoCredito

@Composable
fun CreditosView(navController: NavController, baseDatos: BaseDatos) {
    AppProducto(navController, baseDatos)
}


@Composable
fun AppProducto(navController: NavController, baseDatos: BaseDatos) {
    var precioProducto by remember { mutableStateOf("") }
    var productos by remember { mutableStateOf(listOf<ProductoCredito>()) }
    var idProducto by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    val nombreProducto = "Credito"
    val context = LocalContext.current
    fun mostrarMensaje(mensaje: String) {
        // Mostrar el mensaje en la terminal
        println("MSV: $mensaje")
        // Mostrar el mensaje como un Toast
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(Unit) {
        productos = baseDatos.listarProducto()
    }


        // Sección para ingresar créditos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sección para ingresar créditos
            item {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp)
                )
                Text(
                    text = "Solicita tu $nombreProducto aquí",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = precioProducto,
                    onValueChange = { precioProducto = it },
                    label = { Text(text = "Ingresa el monto") },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .maxWidthIn(280.dp)
                        .padding(bottom = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )
                Button(
                    onClick = {
                        val precio = precioProducto.toIntOrNull()
                        if (precio != null) {
                            val productoCredito = ProductoCredito(
                                nombreproducto = nombreProducto,
                                precio = precio
                            )
                            mensaje = baseDatos.insertarDatos(productoCredito)
                            productos = baseDatos.listarProducto()
                            precioProducto = ""
                        } else {
                            mostrarMensaje("Debe proporcionar una monto válido")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Solicitar crédito", color = Color.White)
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Sección para eliminar créditos
            item {
                OutlinedTextField(
                    value = idProducto,
                    onValueChange = { idProducto = it },
                    label = { Text(text = "N de la solicitud a eliminar") },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.maxWidthIn(280.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val id = idProducto.toIntOrNull()
                        if (id != null) {
                            baseDatos.borrarDatos(idProducto)
                            mostrarMensaje("Crédito eliminado")
                            productos = baseDatos.listarProducto()
                            idProducto = ""
                        } else {
                            mostrarMensaje("Debe proporcionar un N de solicitud para eliminar")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Eliminar solicitud", color = Color.White)
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Sección para actualizar créditos
            item {
                OutlinedTextField(
                    value = idProducto,
                    onValueChange = { idProducto = it },
                    label = { Text(text = "N de la solicitud a actualizar") },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.maxWidthIn(280.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = precioProducto,
                    onValueChange = { precioProducto = it },
                    label = { Text(text = "Nuevo monto del crédito") },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.maxWidthIn(280.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val id = idProducto.toIntOrNull()
                        val precio = precioProducto.toIntOrNull()
                        if (id != null && precio != null) {
                            mensaje = baseDatos.actualizarDatos(idProducto, nombreProducto, precio)
                            productos = baseDatos.listarProducto()
                            idProducto = ""
                            precioProducto = ""
                        } else {
                            mostrarMensaje("Debe proporcionar un N válido y un monto válido")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Actualizar solicitud", color = Color.White)
                }
            }

            // Sección para listar créditos
            item {
                Text(text = "Tus créditos solicitados:")
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    items(productos) { producto ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.creditos),
                                contentDescription = "Creditos logo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(60.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            ) {
                                Text(
                                    text = "SOLICITUD: № ${producto.idproducto}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )
                                Text(
                                    text = "Tipo: ${producto.nombreproducto}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Thin,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )
                                Text(
                                    text = "Monto solicitado: $${producto.precio}",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = FontFamily.Default,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.padding(0.dp)
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Validado",
                                    tint = Color.Cyan,
                                    modifier = Modifier.size(15.dp)
                                )
                                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                                Text(
                                    text = "Validado",
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
                //if (productos.isEmpty()) {
                //    Text("No hay créditos solicitados.")
                //}
            }
        }
    }
}



@Preview
@Composable
fun CreditosViewPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val baseDatos = BaseDatos(context)
    CreditosView(navController, baseDatos)
}