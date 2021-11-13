package com.example.littlebignotepad.dependencyinjection

import com.example.littlebignotepad.MainActivity
import com.example.littlebignotepad.ViewModels.MainPageViewModel
import com.example.littlebignotepad.database.AppDatabase
import com.example.littlebignotepad.database.NoteDao
import com.example.littlebignotepad.utils.DataReader
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