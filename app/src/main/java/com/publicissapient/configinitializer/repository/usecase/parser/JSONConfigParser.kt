package com.publicissapient.configinitializer.repository.usecase.parser

import android.content.res.AssetManager
import com.google.gson.Gson
import com.publicissapient.configinitializer.repository.usecase.parser.ParsingUtils.CONFIG_NAME
import com.publicissapient.configinitializer.model.EnvironmentConfig
import javax.inject.Inject


class JSONConfigParser @Inject constructor(
    private val assets: AssetManager,
    private val gson: Gson
): ConfigParser<EnvironmentConfig> {

    override fun parseConfigRawFile(rawFilePath: String): EnvironmentConfig {
        val json = ParsingUtils.getJsonString(rawFilePath, assets)
        return gson.fromJson(json, EnvironmentConfig::class.java)
    }
}