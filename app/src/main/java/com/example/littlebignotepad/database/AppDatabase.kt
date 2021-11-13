package com.example.littlebignotepad.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): NoteDao
}