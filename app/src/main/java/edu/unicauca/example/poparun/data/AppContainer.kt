package edu.unicauca.example.poparun.data

import android.content.Context

interface AppContainer {
    val PopaRunRepository:PopaRunRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val PopaRunRepository: PopaRunRepository by lazy {
        OfflineUsersRepository(PopaRunDatabase.getDatabase(context).userDao())
    }
}