package com.publicissapient.configinitializer.repository.model

data class ConfigListItem(
    val title: String,
    val type: Component,
    val subtext: String?,
    val subItems: List<SubItem>?
)