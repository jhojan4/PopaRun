package edu.unicauca.aplimovil.poparun.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registros")
data class RegistroCronometro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tiempo: Long,        // en segundos
    val distancia: Double,   // en km
    val velocidad: Double    // en km/h
)