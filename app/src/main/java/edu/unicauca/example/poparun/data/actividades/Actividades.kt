package edu.unicauca.example.poparun.data.actividades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import edu.unicauca.example.poparun.data.user.user

@Entity(
    tableName = "activities",
    foreignKeys = [ForeignKey(
        entity = user::class, // tu clase de usuario
        parentColumns = ["id"], // columna clave en "user"
        childColumns = ["userId"], // columna en esta tabla
        onDelete = ForeignKey.CASCADE
    )]
)
data class Actividades(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int, // 🔗 clave foránea
    val actividad: String,
    val duration: Int,
    val distance: Int,
    val calories: Int,
)
