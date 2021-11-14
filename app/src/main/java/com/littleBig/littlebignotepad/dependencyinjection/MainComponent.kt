package com.littleBig.littlebignotepad.dependencyinjection

import com.littleBig.littlebignotepad.MainActivity
import com.littleBig.littlebignotepad.ViewModels.MainPageViewModel
import com.littleBig.littlebignotepad.database.AppDatabase
import com.littleBig.littlebignotepad.database.NoteDao
import com.littleBig.littlebignotepad.utils.DataReader
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, MainModule::class, RoomModule::class, ViewModelModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPageViewModel: MainPageViewModel)
    fun inject(noteDao: NoteDao)
    fun inject(appDatabase: AppDatabase)
    fun inject(dataReader: DataReader)
}