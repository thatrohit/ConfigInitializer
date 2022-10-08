package com.publicissapient.configinitializer.model

data class Item(
    val descriptionResourceId: String,
    val extra: Any,
    val titleResourceId: String,
    val value: String
)