package edu.unicauca.example.poparun.data

import android.content.Context
import edu.unicauca.example.poparun.data.actividades.ActividadesRepository
import edu.unicauca.example.poparun.data.actividades.OfflineActividadesRepository
import edu.unicauca.example.poparun.data.user.OfflineUsersRepository
import edu.unicauca.example.poparun.data.user.PopaRunRepository

/**
 * Contenedor de dependencias para la app
 */
interface AppContainer {
    val popaRunRepository: PopaRunRepository
    val actividadesRepository: ActividadesRepository
    var loggedInUserId: Int // <- Agrega
}

/**
 * Implementación concreta del contenedor de dependencias
 */
class AppDataContainer(private val context: Context) : AppContainer {

    // Instancia única de la base de datos
    private val database: PopaRunDatabase by lazy {
        PopaRunDatabase.getDatabase(context)
    }

    // Repositorio de usuarios
    override val popaRunRepository: PopaRunRepository by lazy {
        OfflineUsersRepository(database.userDao())
    }

    // Repositorio de actividades
    override val actividadesRepository: ActividadesRepository by lazy {
        OfflineActividadesRepository(database.actividadesDao())
    }
    override var loggedInUserId: Int = 0 // Por ahora un valor fijo o de prueba

}
