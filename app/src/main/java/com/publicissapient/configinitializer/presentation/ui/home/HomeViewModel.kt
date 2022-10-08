package com.publicissapient.configinitializer.presentation.ui.home

import android.content.res.AssetManager
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.publicissapient.configinitializer.repository.model.Config
import com.publicissapient.configinitializer.repository.usecase.mapper.ConfigMapperImpl
import com.publicissapient.configinitializer.repository.usecase.parser.JSONConfigParser

class HomeViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    fun getListItems(assetManager: AssetManager, resources: Resources): Config {
        val parser = JSONConfigParser(assetManager, Gson())
        val parsedResult = parser.parseConfigRawFile("config.json")
        val mapper = ConfigMapperImpl()
        val config = mapper.map(resources, parsedResult)
        return config
    }
}