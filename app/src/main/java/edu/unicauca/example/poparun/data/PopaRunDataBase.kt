package edu.unicauca.example.poparun.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.unicauca.example.poparun.data.actividades.Actividades
import edu.unicauca.example.poparun.data.actividades.ActividadesDao
import edu.unicauca.example.poparun.data.user.user
import edu.unicauca.example.poparun.data.user.userDao


@Database(entities = [user::class, Actividades::class], version = 2, exportSchema = false)

abstract class PopaRunDatabase : RoomDatabase() {

    abstract fun userDao(): userDao
    abstract fun actividadesDao(): ActividadesDao // ⬅️ Añade el DAO para actividades

    companion object {
        @Volatile
        private var Instance: PopaRunDatabase? = null

        fun getDatabase(context: Context): PopaRunDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PopaRunDatabase::class.java,
                    "user_database" // puedes cambiar el nombre si deseas
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
