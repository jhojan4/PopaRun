package edu.unicauca.example.poparun.data.user

import kotlinx.coroutines.flow.Flow

interface PopaRunRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<user>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<user?>
    suspend fun getUserByEmail(email: String): user?

    /**
     * Insert item in the data source
     */
    suspend fun insertUser(user: user)

    /**
     * Delete item from the data source
     */
    suspend fun deleteUser(user: user)

    /**
     * Update item in the data source
     */
    suspend fun updateUser(user: user)

    suspend fun getUserByUsernameAndPassword(email: String, password: String): user?

}
