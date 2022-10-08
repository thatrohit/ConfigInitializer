package com.publicissapient.configinitializer.model

data class EnvironmentConfig(
    val components: List<Component>,
    val target: String,
    val titleResourceId: String
)