package edu.unicauca.example.poparun

import android.app.Application
import edu.unicauca.example.poparun.data.AppContainer
import edu.unicauca.example.poparun.data.AppDataContainer

class PopaRunApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        instance = this
        container = AppDataContainer(this)
    }

    companion object {
        lateinit var instance: PopaRunApp
            private set
    }
}
