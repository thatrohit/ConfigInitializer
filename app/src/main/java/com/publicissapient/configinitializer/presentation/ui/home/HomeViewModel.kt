package com.publicissapient.configinitializer.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.publicissapient.configinitializer.model.EnvironmentConfig
import com.publicissapient.configinitializer.repository.model.Config
import com.publicissapient.configinitializer.repository.usecase.mapper.ConfigMapper
import com.publicissapient.configinitializer.repository.usecase.parser.ConfigParser
import com.publicissapient.configinitializer.repository.usecase.parser.ParsingUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var parser: @JvmSuppressWildcards ConfigParser<EnvironmentConfig>

    @Inject
    lateinit var mapper: @JvmSuppressWildcards ConfigMapper<EnvironmentConfig, Config>

    fun getListItems(): Config {
        val parsedResult = parser.parseConfigRawFile(ParsingUtils.CONFIG_NAME)
        return mapper.map(parsedResult)
    }

}