package com.littleBig.littlebignotepad.utils

import com.littleBig.littlebignotepad.database.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataReader @Inject constructor(private var noteDao: NoteDao) {

    suspend fun getNotes(): List<String>{
        return noteDao.getNotes()
    }

    suspend fun saveNote(note: List<String>){
        return noteDao.saveNotes(note)
    }
}