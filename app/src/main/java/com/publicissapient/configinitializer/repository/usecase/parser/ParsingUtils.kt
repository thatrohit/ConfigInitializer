package com.publicissapient.configinitializer.repository.usecase.parser

import android.content.res.AssetManager
import java.io.IOException
import java.nio.charset.Charset

object ParsingUtils {
    const val CONFIG_NAME = "config.json"
    fun getJsonString(fileName: String, assets: AssetManager): String {
        var json: String
        try {
            val inputStream = assets.open(fileName)
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
        return json
    }
}