package com.example.socios.Views.Logins

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socios.modelo.Producto
import com.example.socios.modelo.Usuario
import com.example.socios.modelo.UsuarioLogin
import com.example.socios.util.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    val isLoading = mutableStateOf(false)
    private val _userCreationResult = MutableStateFlow<UserCreationResult?>(null)
    val userCreationResult = _userCreationResult.asStateFlow()

    private val _productCreationResult = MutableStateFlow<ProductCreationResult?>(null)
    val productCreationResult = _productCreationResult.asStateFlow()

    fun createUser(mail: String, pass: String, nombre: String, apellido: String) {
        val usuario = Usuario(mail, pass, nombre, apellido)

        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.usuarioAlmacenar(usuario)
            if (response.isSuccessful) {
                _userCreationResult.value = UserCreationResult.Success(response.body())
            } else {
                _userCreationResult.value = UserCreationResult.Error(response.message())
            }
            isLoading.value = false
            resetUserCreationResult()
        }
    }

    fun usuarioLogin(mail: String, pass: String) {
        val usuarioLogin = UsuarioLogin(mail, pass)

        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.usuarioLogin(usuarioLogin)
            println("MSV: " + (response.body()?.get(0)?.RESPUESTA ?: "0"))

            if (response.isSuccessful) {
                val cantidad = response.body()?.get(0)?.RESPUESTA
                _userCreationResult.value = UserCreationResult.Success(cantidad)
            } else {
                println("RESPUESTA ERROR")
                //_userCreationResult.value = UserCreationResult.Error(response.message())
            }
            isLoading.value = false
            resetUserCreationResult()
        }
    }

    fun crearProducto(producto: Producto, onResult: (Response<String>) -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.crearProducto(producto)
            onResult(response)
            isLoading.value = false
        }
    }


    fun resetUserCreationResult() {
        viewModelScope.launch {
            delay(1000)
            _userCreationResult.value = null
        }
    }

    sealed class ProductCreationResult {
        data class Success(val message: String): ProductCreationResult()
        data class Error(val message: String): ProductCreationResult()
    }

    sealed class UserCreationResult {
        data class Success(val data: Any?): UserCreationResult()
        data class Error(val message: String): UserCreationResult()
    }
}
@Composable
fun RegisterView(navController: NavController) {
    ContentRegisterView(navController)
}

@Composable
fun ContentRegisterView(navController: NavController) {
    var mail by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel()
    val userCreationResult by viewModel.userCreationResult.collectAsState()

    LaunchedEffect(userCreationResult) {
        when (val result = userCreationResult) {
            is MainViewModel.UserCreationResult.Success -> {
                Toast.makeText(context, "Usuario creado con éxito", Toast.LENGTH_LONG).show()
                viewModel.resetUserCreationResult()
                navController.navigate("Login")
            }
            is MainViewModel.UserCreationResult.Error -> {
                Toast.makeText(context, "Error al crear usuario: ${result.message}", Toast.LENGTH_LONG).show()
                println("Error al crear usuario: ${result.message}")
            }
            null -> {
                println("PASA POR EL NULL")
            }
        }
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text("Creación de Usuario", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = mail,
                onValueChange = { mail = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.createUser(mail, pass, nombre, apellido)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text("Crear Cuenta")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
/*@Composable
fun ContentRegisterView(navController: NavController) {

    var mail by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    var pass2 by remember {
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

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Te damos la bienvenida.", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Ingresa tus datos")

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
            modifier = Modifier.maxWidthIn(280.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = {
                pass = it
            },
            label = {
                Text(text = "Ingresa tu clave")
            },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.maxWidthIn(280.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pass2,
            onValueChange = {
                pass2 = it
            },
            label = {
                Text(text = "Ingresa tu clave nuevamente")
            },
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.maxWidthIn(280.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (mail.isNullOrEmpty() || pass.isNullOrEmpty() || pass2.isNullOrEmpty()) {
                    println("Complete todos los campos.")
                    Toast.makeText(context, "Complete todos los campos.", Toast.LENGTH_SHORT).show()
                } else if (mail.isNullOrEmpty()) {
                    println("Ingrese un mail, por favor.")
                    Toast.makeText(context, "Ingrese un mail, por favor.", Toast.LENGTH_SHORT).show()
                } else if (pass.isNullOrEmpty()) {
                    println("Ingrese una clave. Mínimo 4 a 8 caracteres")
                    Toast.makeText(context, "Ingrese su clave. Mínimo 4 a 8 caracteres", Toast.LENGTH_SHORT).show()
                } else if (!mail.contains("@")) {
                    println("Correo electrónico inválido: Falta el caracter '@'")
                    Toast.makeText(context, "Correo electrónico inválido: Falta el caracter '@'", Toast.LENGTH_LONG).show()
                } else if (!validarCorreoElectronico(mail)) {
                    println("Correo electrónico inválido")
                    Toast.makeText(context, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                }else if (pass != pass2) {
                    println("Las claves deben ser idénticas.")
                    Toast.makeText(context, "Las claves deben ser idénticas.", Toast.LENGTH_SHORT).show()
                } else if (pass.length !in 6..8) {
                    println("La clave debe tener entre 6 y 8 caracteres.")
                    Toast.makeText(context, "La clave debe tener entre 6 y 8 caracteres.", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel().createUser(mail,pass)
                    navController.navigate("Login")
                    println("Usuario registrado")
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                }
            }
            ,
            modifier = Modifier.height(50.dp).maxWidthIn(130.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)

            ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        MainButton(
            name = "¿Olvidaste tu clave?",
            backColor = Color.Transparent,
            color = Color.Black
        ) {
            navController.navigate("Forgot")
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
}*/
/*
                    if (mail.isNullOrEmpty() || pass.isNullOrEmpty() || pass2.isNullOrEmpty()) {
                        println("Complete todos los campos.")
                        Toast.makeText(context, "Complete todos los campos.", Toast.LENGTH_SHORT).show()
                    } else if (mail.isNullOrEmpty()) {
                        println("Ingrese un mail, por favor.")
                        Toast.makeText(context, "Ingrese un mail, por favor.", Toast.LENGTH_SHORT).show()
                    } else if (pass.isNullOrEmpty()) {
                        println("Ingrese una clave. Mínimo 4 a 8 caracteres")
                        Toast.makeText(context, "Ingrese su clave. Mínimo 4 a 8 caracteres", Toast.LENGTH_SHORT).show()
                    } else if (!mail.contains("@")) {
                        println("Correo electrónico inválido: Falta el caracter '@'")
                        Toast.makeText(context, "Correo electrónico inválido: Falta el caracter '@'", Toast.LENGTH_LONG).show()
                    } else if (!validarCorreoElectronico(mail)) {
                        println("Correo electrónico inválido")
                        Toast.makeText(context, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                    }else if (pass != pass2) {
                        println("Las claves deben ser idénticas.")
                        Toast.makeText(context, "Las claves deben ser idénticas.", Toast.LENGTH_SHORT).show()
                    } else if (pass.length !in 6..8) {
                        println("La clave debe tener entre 6 y 8 caracteres.")
                        Toast.makeText(context, "La clave debe tener entre 6 y 8 caracteres.", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.createUser(mail,pass)
                        navController.navigate("Login")
                        println("Usuario registrado")
                        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    }*/