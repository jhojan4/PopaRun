package edu.unicauca.example.poparun.data.user

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: userDao) : PopaRunRepository {
    override fun getAllItemsStream(): Flow<List<user>> = userDao.getAllItems()

    override fun getItemStream(id: Int): Flow<user?> = userDao.getItem(id)

    override suspend fun insertUser(user: user) = userDao.insert(user)

    override suspend fun deleteUser(user: user) = userDao.delete(user)
    override suspend fun getUserByEmail(email: String): user? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun updateUser(user: user) = userDao.update(user)

    override suspend fun getUserByUsernameAndPassword(email: String, password: String): user? {
        return userDao.getUserByUsernameAndPassword(email, password)
    }

}