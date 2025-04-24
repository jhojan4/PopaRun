package edu.unicauca.example.poparun.screens.actividadFinalizada

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.poparun.model.AppDatabase
import edu.unicauca.aplimovil.poparun.model.RegistroCronometro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityFinishedViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val dao = AppDatabase.obtenerInstancia(context).cronometroDao()

    var ultimoRegistro = mutableStateOf<RegistroCronometro?>(null)
        private set

    init {
        cargarUltimoRegistro()
    }

    private fun cargarUltimoRegistro() {
        viewModelScope.launch {
            val registros = withContext(Dispatchers.IO) {
                dao.obtenerRegistros()
            }
            ultimoRegistro.value = registros.lastOrNull()
        }
    }
}
