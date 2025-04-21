package edu.unicauca.example.poparun.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface userDao {
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByUsernameAndPassword(email: String, password: String): user?


    @Query("SELECT * from users ORDER BY name ASC")
    fun getAllItems(): Flow<List<user>>
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): user?

    @Query("SELECT * from users WHERE id = :id")
    fun getItem(id: Int): Flow<user>
    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: user)

    @Update
    suspend fun update(item: user)

    @Delete
    suspend fun delete(item: user)
}