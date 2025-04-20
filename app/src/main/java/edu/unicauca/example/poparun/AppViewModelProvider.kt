package edu.unicauca.example.poparun
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.unicauca.example.poparun.register.RegisterViewModel
import edu.unicauca.example.poparun.data.PopaRunRepository
import edu.unicauca.example.poparun.data.AppContainer

object AppViewModelProvider {
    val Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val app = PopaRunApp.instance
            val repository: PopaRunRepository = (app.container as AppContainer).PopaRunRepository

            @Suppress("UNCHECKED_CAST")
            return when {
                modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                    RegisterViewModel(repository) as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
