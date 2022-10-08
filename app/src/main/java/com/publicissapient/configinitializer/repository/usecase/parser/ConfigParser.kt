package com.publicissapient.configinitializer.repository.usecase.parser

interface ConfigParser<out T> {

    fun parseConfigRawFile(rawFilePath: String) : T
}