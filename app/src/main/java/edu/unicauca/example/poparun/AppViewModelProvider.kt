package edu.unicauca.example.poparun
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.unicauca.example.poparun.login.LoginViewModel
import edu.unicauca.example.poparun.screens.register.RegisterViewModel
import edu.unicauca.example.poparun.screens.StartAct.StartViewModel
import edu.unicauca.example.poparun.screens.home.HomeViewModel


//Acordarse que aqui debo inicializar los viewmodels
object AppViewModelProvider {
    val Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val app = PopaRunApp.instance
            val userRepository = app.container.popaRunRepository
            val actividadesRepository = app.container.actividadesRepository

            @Suppress("UNCHECKED_CAST")
            return when {
                modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                    RegisterViewModel(userRepository) as T

                modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(userRepository) as T

                modelClass.isAssignableFrom(StartViewModel::class.java) -> {
                    val userId = app.container.loggedInUserId // <- Asume que lo guardas
                    StartViewModel(actividadesRepository, userId) as T
                }

                modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                    val userId = app.container.loggedInUserId
                    HomeViewModel(actividadesRepository, userId) as T
                }


                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}

