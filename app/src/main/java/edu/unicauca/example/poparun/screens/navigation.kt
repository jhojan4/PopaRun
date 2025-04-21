package edu.unicauca.example.poparun.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.unicauca.example.poparun.AppViewModelProvider
import edu.unicauca.example.poparun.login.LoginViewModel
import edu.unicauca.example.poparun.screens.StartAct.StartScreen
import edu.unicauca.example.poparun.screens.StartAct.StartViewModel
import edu.unicauca.example.poparun.screens.login.loginScreen
import edu.unicauca.example.poparun.screens.register.RegisterViewModel
import edu.unicauca.example.poparun.screens.register.registerScreen

@Composable
fun AppNavigation(){
    val  navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.name){
        composable(Screens.LoginScreen.name){
            val viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
            loginScreen(navController,viewModel=viewModel)
        }
        composable(Screens.RegistroScreen.name) {
            val viewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
            registerScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screens.StartScreen.name){
            val viewModel: StartViewModel = viewModel(factory = AppViewModelProvider.Factory)
            StartScreen(navController = navController, viewModel = viewModel)
        }

    }
}
enum class Screens(){
    PresentacionScreen,
    LoginScreen,
    RegistroScreen,
    HomeScreen,
    StartScreen

}