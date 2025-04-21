package edu.unicauca.example.poparun.data.actividades

import kotlinx.coroutines.flow.Flow

interface ActividadesRepository {
    fun getAllActivitiesForUser(userId: Int): Flow<List<Actividades>>
    fun getActivityById(id: Int): Flow<Actividades?>
    suspend fun insertActividad(actividad: Actividades)
    suspend fun deleteActividad(actividad: Actividades)
    suspend fun updateActividad(actividad: Actividades)
}
