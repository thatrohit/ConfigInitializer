package com.publicissapient.configinitializer.repository.usecase.parser

import android.content.res.AssetManager
import com.google.gson.Gson
import com.publicissapient.configinitializer.model.EnvironmentConfig
import java.io.IOException
import java.nio.charset.Charset

class JSONConfigParser(
    private val assets: AssetManager,
    private val gson: Gson
): ConfigParser<EnvironmentConfig> {

    override fun parseConfigRawFile(rawFilePath: String): EnvironmentConfig {
        var json: String
        try {
            val inputStream = assets.open(rawFilePath)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            json = ""
            ex.printStackTrace()
        }
        val parsedJson = gson.fromJson(json, EnvironmentConfig::class.java)
        return parsedJson
    }
}