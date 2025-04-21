package edu.unicauca.aplimovil.poparun.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CronometroViewModel : ViewModel() {

    private val _tiempo = MutableStateFlow(0L)
    val tiempo = _tiempo.asStateFlow()

    private var job: Job? = null

    fun iniciar() {
        if (job == null) {
            job = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _tiempo.value += 1
                }
            }
        }
    }

    fun detener() {
        job?.cancel()
        job = null
    }

    fun reiniciar() {
        detener()
        _tiempo.value = 0L
    }
}