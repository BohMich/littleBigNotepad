package com.littleBig.littlebignotepad.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littleBig.littlebignotepad.utils.DataCallback
import com.littleBig.littlebignotepad.utils.DataReader
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPageViewModel @Inject constructor(private val dataReader: DataReader)
    : ViewModel() {

    private var noteCache = arrayListOf<String>()

    fun setup(callback: DataCallback){
        noteCache.add(0,"pos1")
        noteCache.add(1,"pos2")

        viewModelScope.launch {
            noteCache = dataReader.getNotes() as ArrayList<String>
            callback.onSuccessRead(noteCache[0])
        }
    }

    fun close(){
        saveNotes(noteCache)
    }

    fun getNotes(callback: DataCallback, noteNo: Int) {
            callback.onSuccessRead(noteCache[noteNo])
    }

    private fun saveNotes(note: List<String>){
        viewModelScope.launch {
            dataReader.saveNote(note)
        }
    }

    fun cacheNotes(note: String, noteNo: Int){
        noteCache[noteNo] = note
    }
}