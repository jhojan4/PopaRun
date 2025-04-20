package edu.unicauca.example.poparun

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.unicauca.example.poparun.login.loginScreen
import edu.unicauca.example.poparun.register.RegisterViewModel
import edu.unicauca.example.poparun.register.registerScreen

@Composable
fun AppNavigation(){
    val  navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screens.RegistroScreen.name){
        composable(Screens.LoginScreen.name){
            loginScreen(navController)
        }
        composable(Screens.RegistroScreen.name) {
            val viewModel: RegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
            registerScreen(navController = navController, viewModel = viewModel)
        }

    }
}
enum class Screens(){
    PresentacionScreen,
    LoginScreen,
    RegistroScreen,
    HomeScreen

}