package com.example.littlebignotepad.dependencyinjection

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.littlebignotepad.database.AppDatabase
import com.example.littlebignotepad.database.DatabaseBuilder
import com.example.littlebignotepad.database.NoteDao
import com.example.littlebignotepad.utils.DataReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(app: MultiDexApplication?) {

    private var context: Context = app!!

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return DatabaseBuilder.buildDatabase(context)
    }

    @Singleton
    @Provides
    fun providesNoteDao(database: AppDatabase): NoteDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun providesDataReader(noteDao: NoteDao): DataReader {
        return DataReader(noteDao)
    }

}