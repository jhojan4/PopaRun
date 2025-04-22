package edu.unicauca.example.poparun.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.unicauca.example.poparun.data.actividades.ActividadesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ActividadesRepository,
    private val userId: Int
) : ViewModel() {

    private val _userName = MutableStateFlow("Usuario") // se puede actualizarlo desde UserRepository
    val userName: StateFlow<String> = _userName

    private val _dailyGoalCalories = MutableStateFlow(500) // meta predefinida
    val dailyGoalCalories: StateFlow<Int> = _dailyGoalCalories

    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories

    private val _completedWorkouts = MutableStateFlow(0)
    val completedWorkouts: StateFlow<Int> = _completedWorkouts

    private val _totalWorkouts = MutableStateFlow(0)
    val totalWorkouts: StateFlow<Int> = _totalWorkouts

    init {
        cargarActividades()
    }

    private fun cargarActividades() {
        viewModelScope.launch {
            repository.getAllActivitiesForUser(userId).collect { lista ->
                _totalWorkouts.value = lista.size
                _completedWorkouts.value = lista.size // se puede añadir un campo estado si necesitas filtrar.
                _totalCalories.value = lista.sumOf { it.calories }
            }
        }
    }
}
