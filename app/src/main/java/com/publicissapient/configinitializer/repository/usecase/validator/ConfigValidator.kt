package com.publicissapient.configinitializer.repository.usecase.validator

import com.publicissapient.configinitializer.model.EnvironmentConfig

interface ConfigValidator {
    fun validate(config: EnvironmentConfig): List<String>
}