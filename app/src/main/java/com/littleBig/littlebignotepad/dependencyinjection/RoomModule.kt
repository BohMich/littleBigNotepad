package com.littleBig.littlebignotepad.dependencyinjection

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.littleBig.littlebignotepad.database.AppDatabase
import com.littleBig.littlebignotepad.database.DatabaseBuilder
import com.littleBig.littlebignotepad.database.NoteDao
import com.littleBig.littlebignotepad.utils.DataReader
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