package com.publicissapient.configinitializer.presentation.ui.validator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import com.publicissapient.configinitializer.presentation.theme.ConfigInitializerTheme
import com.publicissapient.configinitializer.presentation.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValidatorActivity: ComponentActivity() {

    lateinit var viewModel: ValidatorViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ValidatorViewModel::class.java]
        viewModel.validate().observe(this) {
            setContent {
                ConfigInitializerTheme {
                    Log.d("VALIDATOR", it.toString())
                    if (it.isEmpty()) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        ListItem (
                            headlineText = {Text("Errors found in config.json")},
                            supportingText = {Text(it.joinToString("\n"))},
                            leadingContent = {Icon(Icons.Filled.Clear, "Errors found in config.json")}
                        )
                    }
                }
            }
        }
    }

}