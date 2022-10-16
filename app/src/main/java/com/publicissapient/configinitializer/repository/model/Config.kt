package com.publicissapient.configinitializer.repository.model

data class Config (
    val title: String,
    val targetPackage: String,
    val targetActivity: String,
    val items: List<ConfigListItem>
)