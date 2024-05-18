package com.example.socios.Screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socios.Components.generateDummyMessages
import com.example.socios.Views.Logins.ForgotView
import com.example.socios.Views.Logins.LoginView
import com.example.socios.Views.Logins.MainViewModel
import com.example.socios.Views.Logins.RegisterView
import com.example.socios.Views.Main.HomeView
import com.example.socios.Views.Main.ProfileView
import com.example.socios.Views.Main.ServicesView
import com.example.socios.Views.Resources.ConfigurationView
import com.example.socios.Views.Resources.ContactView
import com.example.socios.Views.Resources.DirectionsView
import com.example.socios.Views.Resources.NotificationsView
import com.example.socios.Views.Resources.SearchView
import com.example.socios.Views.Resources.SplashView


@Composable
fun NavManager() {
    val navController = rememberNavController()
    val messages = remember { generateDummyMessages() }
    NavHost(navController = navController, startDestination = "Register") {

        //Logins
        composable("Login") {
            LoginView(navController)
        }
        composable("Register") {
            RegisterView(navController)
        }
        composable("Forgot") {
            ForgotView(navController)
        }

        //Resources
        composable("Directions") {
            DirectionsView(navController)
        }
        composable("Contact") {
            ContactView(navController)
        }
        composable("Splash") {
            SplashView(navController)
        }
        composable("Notifications") {
            NotificationsView(navController, messages = messages)
        }
        composable("Configuration") {
            ConfigurationView(navController)
        }
        composable("Search") {
            SearchView(navController)
        }

        //Main
        composable("Home") {
            HomeView(navController)
        }
        composable("Profile") {
            ProfileView(navController)
        }
        composable("Services") {
            ServicesView(navController, mainViewModel = viewModel())
        }

    }
}
