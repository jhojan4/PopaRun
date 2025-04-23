package edu.unicauca.aplimovil.poparun.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.aplimovil.poparun.model.AppDatabase
import edu.unicauca.aplimovil.poparun.model.RegistroCronometro
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CronometroViewModel (application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.obtenerInstancia(application).cronometroDao()


    private val _tiempo = MutableStateFlow(0L)
    val tiempo = _tiempo.asStateFlow()

    private val _distancia = MutableStateFlow(0.0)  // en km
    val distancia = _distancia.asStateFlow()

    private val _velocidad = MutableStateFlow(0.0)  // en km/h
    val velocidad = _velocidad.asStateFlow()

    private var corriendo = false

    fun iniciar() {
        if (!corriendo) {
            corriendo = true
            viewModelScope.launch {
                var contadorVelocidad = 0
                while (corriendo) {
                    delay(1000)
                    _tiempo.value += 1

                    // Simulación de valores
                    _distancia.value += 0.01  // aumenta 10 metros por segundo

                    contadorVelocidad += 1

                    if (contadorVelocidad >= 10) {
                        val tiempoHoras = _tiempo.value / 3600.0  // convertir a horas
                        _velocidad.value = if (tiempoHoras > 0) {
                            _distancia.value / tiempoHoras
                        } else {
                            0.0
                        }
                        contadorVelocidad = 0
                    }


                }
            }
        }
    }

    fun detener() {
        corriendo = false
    }

    private fun guardarRegistro() {
        viewModelScope.launch {
            val registro = RegistroCronometro(
                tiempo = _tiempo.value,
                distancia = _distancia.value,
                velocidad = _velocidad.value
            )
            dao.insertarRegistro(registro)
        }
    }



    fun reiniciar() {
        corriendo = false
        _tiempo.value = 0L
        _distancia.value = 0.0
        _velocidad.value = 0.0
    }
}