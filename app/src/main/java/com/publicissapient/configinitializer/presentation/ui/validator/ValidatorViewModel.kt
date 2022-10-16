package com.publicissapient.configinitializer.presentation.ui.validator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.publicissapient.configinitializer.model.EnvironmentConfig
import com.publicissapient.configinitializer.repository.usecase.parser.ConfigParser
import com.publicissapient.configinitializer.repository.usecase.parser.ParsingUtils.CONFIG_NAME
import com.publicissapient.configinitializer.repository.usecase.validator.ConfigValidatorImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ValidatorViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var validator: ConfigValidatorImpl

    @Inject
    lateinit var parser: ConfigParser<EnvironmentConfig>

    override fun onCleared() {
        super.onCleared()

    }

    fun validate() : LiveData<List<String>> {
        return MutableLiveData(validator.validate(parser.parseConfigRawFile(CONFIG_NAME)))
    }
}