package edu.unicauca.aplimovil.poparun.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegistroCronometro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cronometroDao(): CronometroDao

    companion object {
        @Volatile private var instancia: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase {
            return instancia ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cronometro.db"
                ).build().also { instancia = it }
            }
        }
    }
}