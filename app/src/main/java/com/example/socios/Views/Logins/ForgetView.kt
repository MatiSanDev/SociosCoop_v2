package com.example.socios.Views.Logins

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.maxWidthIn
import com.example.socios.Components.validarCorreoElectronico
import com.example.socios.R

@Composable
fun ForgotView(navController: NavController) {
    ContentForgotView(navController)
}

@Composable
fun ContentForgotView(navController: NavController) {
    var mail by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Te ayudamos", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Recupera tu clave")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mail,
            onValueChange = {
                mail = it
            },
            label = {
                Text(text = "Ingresa tu mail.")
            },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.maxWidthIn(310.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("Rut: $mail")
                if (mail.isNullOrEmpty()) {
                    println("Ingrese un mail, por favor.")
                    Toast.makeText(context, "Ingrese un mail, por favor.", Toast.LENGTH_SHORT).show()
                } else if (!mail.contains("@")) {
                    println("Correo electrónico inválido: Falta el caracter '@'")
                    Toast.makeText(context, "Correo electrónico inválido: Falta el caracter '@'", Toast.LENGTH_LONG).show()
                } else if (!validarCorreoElectronico(mail)) {
                    println("Correo electrónico inválido")
                    Toast.makeText(context, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate("Login")
                    println("Se ha enviado un correo electrónico para recuperar la clave.")
                    Toast.makeText(context, "Se ha enviado un correo electrónico para recuperar la clave.", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.height(50.dp).maxWidthIn(130.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        )
        {
            Text("Recuperar")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 40.dp),
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ubicacion),
                    contentDescription = "Sucursal logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("Directions")
                        })
                Text(text = "Sucursales", fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.fono),
                    contentDescription = "Fono logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("Contact")
                        })

                Text(text = "Contacto", fontSize = 12.sp)
            }
        }
    }
}
@Preview
@Composable
fun ForgotViewPreview() {
    val navController = rememberNavController()
    ForgotView(navController)
}