package edu.unicauca.aplimovil.poparun.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CronometroDao {
    @Insert
    suspend fun insertarRegistro(registro: RegistroCronometro)

    @Query("SELECT * FROM registros")
    suspend fun obtenerRegistros(): List<RegistroCronometro>
}