package com.publicissapient.configinitializer.repository.model

data class ConfigListItem(
    val id: String,
    val title: String,
    val type: Component,
    val subtext: String?,
    val subItems: List<SubItem>?
)