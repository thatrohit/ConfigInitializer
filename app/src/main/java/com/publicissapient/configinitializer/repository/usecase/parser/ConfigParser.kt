package com.publicissapient.configinitializer.repository.usecase.parser

interface ConfigParser<out ParsedModel> {
    fun parseConfigRawFile(rawFilePath: String) : ParsedModel
}