package com.littleBig.littlebignotepad.dependencyinjection

import android.content.Context
import androidx.multidex.MultiDexApplication

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