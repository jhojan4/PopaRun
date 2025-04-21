package edu.unicauca.example.poparun.data.actividades

import edu.unicauca.example.poparun.data.user.PopaRunRepository
import edu.unicauca.example.poparun.data.user.user
import edu.unicauca.example.poparun.data.user.userDao
import kotlinx.coroutines.flow.Flow


class OfflineActividadesRepository(
    private val actividadesDao: ActividadesDao
) : ActividadesRepository {

    override fun getAllActivitiesForUser(userId: Int): Flow<List<Actividades>> =
        actividadesDao.getAllActivitiesForUser(userId)

    override fun getActivityById(id: Int): Flow<Actividades?> =
        actividadesDao.getActivityById(id)

    override suspend fun insertActividad(actividad: Actividades) =
        actividadesDao.insert(actividad)

    override suspend fun deleteActividad(actividad: Actividades) =
        actividadesDao.delete(actividad)

    override suspend fun updateActividad(actividad: Actividades) =
        actividadesDao.update(actividad)
}
