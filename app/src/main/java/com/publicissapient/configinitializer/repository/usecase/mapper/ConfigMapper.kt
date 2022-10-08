package com.publicissapient.configinitializer.repository.usecase.mapper

import android.content.res.Resources

interface ConfigMapper<in ParsedRaw, out UiModel> {
    fun map(resource: Resources, parsedJson: ParsedRaw): UiModel
}