package com.littleBig.littlebignotepad.database

import androidx.room.*

@Dao
interface NoteDao {

    @Transaction
    suspend fun preloadData() {
        nukeTable()
        insertNote(NoteEntry(1, "Your first note area"))
        insertNote(NoteEntry(2, "Your second note area"))
    }

    @Transaction
    suspend fun saveNotes(note: List<String>) {
        update(note[0], 1)
        update(note[1], 2)
    }

    @Transaction
    suspend fun getNotes(): List<String> {
        val notes = getAll()
        if (notes.isEmpty()) preloadData()
        return getAll()
    }

    @Query("UPDATE NoteEntry SET note_main_content=:note WHERE uid = :id")
    suspend fun update(note: String, id: Int)

    @Query("SELECT note_main_content FROM NoteEntry")
    suspend fun getAll(): List<String>

    @Query("SELECT note_main_content FROM NoteEntry WHERE uid = :noteID")
    suspend fun loadById(noteID: Int): String

    @Insert
    suspend fun insertNote(note: NoteEntry)

    @Insert
    fun insertAll(vararg notes: NoteEntry)

    @Query("DELETE FROM NoteEntry")
    fun nukeTable()

    @Delete
    fun delete(user: NoteEntry)
}