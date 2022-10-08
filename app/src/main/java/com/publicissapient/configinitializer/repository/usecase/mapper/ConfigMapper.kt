package com.publicissapient.configinitializer.repository.usecase.mapper

interface ConfigMapper<in ParsedRaw, out UiModel> {
    fun map(parsedJson: ParsedRaw): UiModel
}