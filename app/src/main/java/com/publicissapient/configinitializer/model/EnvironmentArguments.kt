package com.publicissapient.configinitializer.com.publicissapient.configinitializer.model

data class EnvironmentArguments(
    val environmentUrl: String,
    val networkLogger: Boolean = false,
    val abTesting: Boolean = false,
    val customArguments: String
)