package edu.unicauca.example.poparun.screens.StartAct
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.example.poparun.data.actividades.Actividades
import edu.unicauca.example.poparun.data.actividades.ActividadesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StartViewModel(
    private val actividadesRepository: ActividadesRepository,
    private val userId: Int // este debe pasarse desde el usuario logueado
) : ViewModel() {

    private val _actividad = MutableStateFlow("Caminar") // por defecto
    val actividad: StateFlow<String> = _actividad

    private val _duration = MutableStateFlow("")
    val duration: StateFlow<String> = _duration

    private val _distance = MutableStateFlow("")
    val distance: StateFlow<String> = _distance

    private val _calories = MutableStateFlow("")
    val calories: StateFlow<String> = _calories

    fun onActividadChange(tipo: String) {
        _actividad.value = tipo
    }

    fun onDurationChange(value: String) {
        _duration.value = value
    }

    fun onDistanceChange(value: String) {
        _distance.value = value
    }

    fun onCaloriesChange(value: String) {
        _calories.value = value
    }

    fun guardarActividad() {
        viewModelScope.launch {
            val actividad = Actividades(
                userId = userId,
                actividad = _actividad.value,
                duration = _duration.value.toIntOrNull() ?: 0,
                distance = _distance.value.toIntOrNull() ?: 0,
                calories = _calories.value.toIntOrNull() ?: 0
            )
            actividadesRepository.insertActividad(actividad)
        }
    }
}
