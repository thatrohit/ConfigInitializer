package com.publicissapient.configinitializer.presentation.ui.picker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider

class PickerActivity: ComponentActivity() {

    lateinit var viewModel: PickerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PickerViewModel::class.java]
    }
}