package com.publicissapient.configinitializer.repository.usecase.mapper

import android.annotation.SuppressLint
import android.content.res.Resources
import com.publicissapient.configinitializer.BuildConfig
import com.publicissapient.configinitializer.model.EnvironmentConfig
import com.publicissapient.configinitializer.repository.model.Component
import com.publicissapient.configinitializer.repository.model.Config
import com.publicissapient.configinitializer.repository.model.ConfigListItem
import com.publicissapient.configinitializer.repository.model.SubItem
import android.content.res.Resources.NotFoundException
import javax.inject.Inject

class ConfigMapperImpl @Inject constructor(
    private val resource: Resources
): ConfigMapper<EnvironmentConfig, Config> {

    override fun map(parsedJson: EnvironmentConfig): Config {
        fun String?.resolveString(): String {
            val resId = resource.getIdentifier(
                this,
                "string",
                BuildConfig.APPLICATION_ID
            )
            return try {
                resource.getString(resId)
            } catch (ex: NotFoundException) {
                ""
            }
        }
        val configListItems = mutableListOf<ConfigListItem>()
        parsedJson.components?.forEach { component ->
            val configListSubItems = mutableListOf<SubItem>()
            component.items?.forEach { subItem ->
                configListSubItems.add(
                    SubItem(
                        id = subItem.titleResourceId ?: "",
                        title = subItem.titleResourceId.resolveString(),
                        subtext = subItem.descriptionResourceId.resolveString(),
                        value = subItem.value ?: ""
                    )
                )
            }
            configListItems.add(
                ConfigListItem(
                    id = component.titleResourceId ?: "",
                    title = component.titleResourceId.resolveString(),
                    type = Component.getComponent(component.type),
                    subtext = component.descriptionResourceId.resolveString(),
                    subItems = configListSubItems
                )
            )
        }
        var config = Config(
            title = parsedJson.titleResourceId.resolveString(),
            targetPackage = parsedJson.target ?: "",
            targetActivity = parsedJson.targetActivity ?: "",
            items = configListItems
        )

        return config
    }
}