package com.example.socios.Views.Resources

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.socios.Components.MyBottomAppBar
import com.example.socios.Components.MySubText
import com.example.socios.Components.MyText
import com.example.socios.Components.MyTopAppBar
import com.example.socios.Components.TitleView
import com.example.socios.R

private val messages: List<MyMessage> = listOf(
    )

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
data class MyMessage(val title: String, val body: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationsView(navController: NavController, messages: List<MyMessage>) {
    Scaffold(
        topBar = {
            MyTopAppBar(navController)
        },
        bottomBar = {
            MyBottomAppBar(navController)
        }
    ) {
        ContentNotificationsView(navController = navController, messages = messages)
    }
}

@Composable
fun ContentNotificationsView(navController: NavController, messages: List<MyMessage>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 75.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleView(name = "Notificaciones")
        LazyColumn {
            items(messages) { message ->
                MyComponent(message)
            }
        }
    }
}

@Composable
fun MyComponent(message: MyMessage) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .clickable {
                expanded = !expanded
            }
            .padding(15.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row {
            MyImage()
            MyTexts(message, expanded)
        }
    }
}

@Composable
fun MyImage() {
    Image(
        painterResource(R.drawable.iso3),
        "Imagen de notificacion",
        modifier = Modifier
            .clip(CircleShape)
            .size(64.dp)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun MyTexts(message: MyMessage, expanded: Boolean) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
    ) {
        MyText(
            text = message.title,
            style = MaterialTheme.typography.titleMedium
        )
        MySubText(
            text = message.body,
            color = Color.Gray,
            style = MaterialTheme.typography.labelMedium,
            lines = if (expanded) Int.MAX_VALUE else 1
        )
    }
}



