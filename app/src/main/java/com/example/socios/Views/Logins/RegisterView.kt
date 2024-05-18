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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socios.Components.MainButton
import com.example.socios.Components.maxWidthIn
import com.example.socios.Components.validarCorreoElectronico
import com.example.socios.R
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

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos = _productos.asStateFlow()

    fun createUser(mail: String, pass: String, nombre: String, apellido: String, navController: NavController) {
        val usuario = Usuario(mail, pass, nombre, apellido)

        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.usuarioLogin(UsuarioLogin(mail, pass))

            if (response.isSuccessful) {
                val cantidad = response.body()?.get(0)?.RESPUESTA
                if (cantidad == "LOGIN NOK") {
                    // El usuario no existe, podemos crearlo
                    val createUserResponse = RetrofitInstance.api.usuarioAlmacenar(usuario)
                    if (createUserResponse.isSuccessful) {
                        _userCreationResult.value = UserCreationResult.Success(createUserResponse.body())
                        // Navegar al login solo si el usuario se crea con éxito
                        navController.navigate("Login")
                    } else {
                        _userCreationResult.value = UserCreationResult.Error(createUserResponse.message())
                    }
                } else {
                    // El usuario ya existe, mostramos un mensaje o hacemos algo
                    _userCreationResult.value = UserCreationResult.Error("El usuario ya existe")
                }
            } else {
                _userCreationResult.value = UserCreationResult.Error(response.message())
            }

            isLoading.value = false
            resetUserCreationResult()
        }
    }

    fun usuarioLogin(mail: String, pass: String, navController: NavController) {
        val usuarioLogin = UsuarioLogin(mail, pass)

        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.usuarioLogin(usuarioLogin)
            println("MSV: " + (response.body()?.get(0)?.RESPUESTA ?: "0"))

            if (response.isSuccessful) {
                val cantidad = response.body()?.get(0)?.RESPUESTA
                if (cantidad == "LOGIN OK") {
                    // Navegar al home solo si el inicio de sesión es exitoso
                    navController.navigate("Home")
                } else {
                    // Mostrar mensaje de error si el inicio de sesión falla
                    _userCreationResult.value = UserCreationResult.Error("Credenciales Inválidas")
                }
            } else {
                // Manejar error de conexión u otros errores de la API
                _userCreationResult.value = UserCreationResult.Error(response.message())
            }
            isLoading.value = false
            resetUserCreationResult()
        }
    }
    fun crearProducto(producto: Producto, onResult: (Response<String>) -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            val response = RetrofitInstance.api.crearProducto(producto)
            println("Response: $response")
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
                Toast.makeText(context, "${result.message}", Toast.LENGTH_LONG).show()
                println("Error al crear usuario: ${result.message}")
                // Agregar condición para evitar la navegación al login
                if (result.message != "El usuario ya existe") {
                    navController.navigate("Login")
                }
            }
            null -> {
                println("PASA POR EL NULL")
            }
        }
    }
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
        modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Te damos la bienvenida.", fontSize = 25.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Ingresa tus datos")

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
                if (mail.isNullOrEmpty() || pass.isNullOrEmpty() || nombre.isNullOrEmpty() || apellido.isNullOrEmpty()) {
                    println("Complete todos los campos.")
                    Toast.makeText(context, "Complete todos los campos.", Toast.LENGTH_SHORT).show()
                } else if (mail.isNullOrEmpty()) {
                    println("Ingrese un mail, por favor.")
                    Toast.makeText(context, "Ingrese un mail, por favor.", Toast.LENGTH_SHORT).show()
                } else if (pass.isNullOrEmpty()) {
                    println("Ingrese una clave. 5 caracteres")
                    Toast.makeText(context, "Ingrese su clave. 5 caracteres", Toast.LENGTH_SHORT).show()
                } else if (nombre.isNullOrEmpty()) {
                    println("Indique su nombre, por favor.")
                    Toast.makeText(context, "Indique su nombre, por favor.", Toast.LENGTH_SHORT)
                        .show()
                } else if (apellido.isNullOrEmpty()) {
                    println("Indique su apellido, por favor.")
                    Toast.makeText(context, "Indique su apellido, por favor.", Toast.LENGTH_SHORT)
                        .show()
                } else if (!mail.contains("@")) {
                    println("Correo electrónico inválido")
                    Toast.makeText(context, "Correo electrónico inválido", Toast.LENGTH_LONG).show()
                } else if (!validarCorreoElectronico(mail)) {
                    println("Correo electrónico inválido")
                    Toast.makeText(context, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                }else if (pass.length !=5) {
                    println("La clave debe tener 5 caracteres.")
                    Toast.makeText(context, "La clave debe tener 5 caracteres.", Toast.LENGTH_SHORT).show()
                } else {
                    // Llama a createUser pasando el NavController como parámetro
                    viewModel.createUser(mail, pass, nombre, apellido, navController)
                }
            }
            ,
            modifier = Modifier.height(50.dp).maxWidthIn(130.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)

        ) {
            Text("Crear Cuenta")
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
}
