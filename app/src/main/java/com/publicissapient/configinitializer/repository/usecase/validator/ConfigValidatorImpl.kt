package com.publicissapient.configinitializer.repository.usecase.validator

import android.content.res.Resources
import com.publicissapient.configinitializer.R
import com.publicissapient.configinitializer.model.EnvironmentConfig
import javax.inject.Inject

class ConfigValidatorImpl @Inject constructor(
    private val resources: Resources
): ConfigValidator {

    private val validComponents = mutableListOf("picker", "switch", "text")

    override fun validate(config: EnvironmentConfig): List<String> {
        val errors = mutableListOf<String>()
        with(config) {
            if(titleResourceId == null) {
                errors.add("${resources.getString(R.string.missing_item)}: titleResourceId")
            }
            if(target == null) {
                errors.add("${resources.getString(R.string.missing_item)}: target")
            }
            if(targetActivity == null) {
                errors.add("${resources.getString(R.string.missing_item)}: targetActivity")
            }
            if(components == null) {
                errors.add("${resources.getString(R.string.missing_item)}: components")
            }
            components?.forEachIndexed { index, component ->
                with(component) {
                    if(type == null) {
                        errors.add(
                            "${resources.getString(R.string.for_component)} $index," +
                            " ${resources.getString(R.string.missing_item)}: type"
                        )
                    }
                    if(!validComponents.contains(type)) {
                        errors.add(
                            "${resources.getString(R.string.for_component)} $index," +
                            " ${resources.getString(R.string.missing_item)}: type"
                        )
                    }
                    if(titleResourceId == null) {
                        errors.add(
                            "${resources.getString(R.string.for_component)} $index," +
                            " ${resources.getString(R.string.missing_item)}: titleResourceId"
                        )
                    }
                    items?.forEachIndexed { itemIndex, item ->
                        if(item.titleResourceId == null) {
                            errors.add(
                                "${resources.getString(R.string.for_item)} $itemIndex," +
                                " ${resources.getString(R.string.for_component)} $index," +
                                " ${resources.getString(R.string.missing_item)}: titleResourceId"
                            )
                        }
                        if(item.value == null) {
                            errors.add(
                                "${resources.getString(R.string.for_item)} $itemIndex," +
                                " ${resources.getString(R.string.for_component)} $index," +
                                " ${resources.getString(R.string.missing_item)}: value")
                        }
                    }
                }
            }
        }
        return errors
    }
}