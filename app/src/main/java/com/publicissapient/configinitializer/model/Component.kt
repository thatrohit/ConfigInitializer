package com.publicissapient.configinitializer.model

data class Component(
    val descriptionResourceId: String,
    val extra: Any,
    val items: List<Item>?,
    val titleResourceId: String,
    val type: String
)