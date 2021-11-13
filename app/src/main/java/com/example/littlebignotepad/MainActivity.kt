package com.example.littlebignotepad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.littlebignotepad.ViewModels.MainPageViewModel
import com.example.littlebignotepad.dependencyinjection.LittleBigNotePad2
import com.example.littlebignotepad.utils.DataCallback
import com.example.littlebignotepad.utils.Helper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_top_panel.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), DataCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainPageViewModel

    init {
        LittleBigNotePad2.mainComponent.inject(this)
    }

    // Lifecycle overrides

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainPageViewModel::class.java)
        viewModel.setup(this)

        // Setup Switch
        setupSwitch()

        //Load Data
        viewModel.getNotes(this, Helper.booleanToInt(include.switch_note_switch2.isChecked))

        //Setup text
        setupTextInput()
    }

    override fun onStop() {
        super.onStop()

        saveNotes(Helper.booleanToInt(include.switch_note_switch2.isChecked))
        viewModel.close()
    }

    // Setup UI methods

    private fun setupSwitch(){

        include.switch_note_switch2.setOnCheckedChangeListener { nt , isChecked ->
            if(isChecked){
                saveNotes(0)
                getNotes(1)
            }
            else{
                saveNotes(1)
                getNotes(0)
            }
        }
    }

    private fun setupTextInput(){
        edittext_Notepad_view.requestFocus()
    }

    // Helper methods

    private fun getNotes(noteNo: Int){
        viewModel.getNotes(this, noteNo)
    }

    private fun loadNotes(note: String){
        edittext_Notepad_view.setText(note)
    }

    private fun saveNotes(noteNo: Int){
        viewModel.cacheNotes(edittext_Notepad_view.text.toString(), noteNo)
    }

    // DataCallback override functions

    override fun onSuccessRead(payload: Any) {
        if ((payload as? String) != null) {
            loadNotes(payload)
            //setViewState(STATE_CONTENT_LOADED)
        } else {
            //setViewState(STATE_LOADING_ERROR)
        }
    }
}