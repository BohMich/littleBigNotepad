package com.example.littlebignotepad.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntry (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "note_main_content") val noteMainContent: String?
)