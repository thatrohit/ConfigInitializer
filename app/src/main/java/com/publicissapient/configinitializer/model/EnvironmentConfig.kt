package com.publicissapient.configinitializer.model

data class EnvironmentConfig(
    val components: List<Component>?,
    val target: String?,
    val targetActivity: String?,
    val titleResourceId: String?
)