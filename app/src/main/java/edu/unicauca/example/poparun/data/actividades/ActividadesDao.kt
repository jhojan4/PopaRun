package edu.unicauca.example.poparun.data.actividades

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ActividadesDao {

    @Query("SELECT * FROM activities WHERE userId = :userId ORDER BY id DESC")
    fun getAllActivitiesForUser(userId: Int): Flow<List<Actividades>>

    @Query("SELECT * FROM activities WHERE id = :id")
    fun getActivityById(id: Int): Flow<Actividades?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(actividad: Actividades)

    @Update
    suspend fun update(actividad: Actividades)

    @Delete
    suspend fun delete(actividad: Actividades)
}
