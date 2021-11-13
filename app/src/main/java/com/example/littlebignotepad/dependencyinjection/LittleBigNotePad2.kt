package com.example.littlebignotepad.dependencyinjection

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.littlebignotepad.database.AppDatabase

class LittleBigNotePad2 : MultiDexApplication() {

    companion object {
        lateinit var appContext: Context
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(this))
            .mainModule(MainModule())
            .roomModule(RoomModule(this)).build()
    }
}