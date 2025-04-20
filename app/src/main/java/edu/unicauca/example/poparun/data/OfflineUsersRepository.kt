package edu.unicauca.example.poparun.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: userDao) : PopaRunRepository {
    override fun getAllItemsStream(): Flow<List<user>> = userDao.getAllItems()

    override fun getItemStream(id: Int): Flow<user?> = userDao.getItem(id)

    override suspend fun insertUser(user: user) = userDao.insert(user)

    override suspend fun deleteUser(user: user) = userDao.delete(user)

    override suspend fun updateUser(user: user) = userDao.update(user)
}