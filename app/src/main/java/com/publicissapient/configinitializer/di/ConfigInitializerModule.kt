package com.publicissapient.configinitializer.di

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import com.google.gson.Gson
import com.publicissapient.configinitializer.model.EnvironmentConfig
import com.publicissapient.configinitializer.repository.model.Config
import com.publicissapient.configinitializer.repository.usecase.mapper.ConfigMapper
import com.publicissapient.configinitializer.repository.usecase.mapper.ConfigMapperImpl
import com.publicissapient.configinitializer.repository.usecase.parser.ConfigParser
import com.publicissapient.configinitializer.repository.usecase.parser.JSONConfigParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object ConfigInitializerModule {

    @Provides
    fun bindConfigParser(
        assets: AssetManager,
        gson: Gson
    ): ConfigParser<EnvironmentConfig> {
        return JSONConfigParser(
            assets,
            gson
        )
    }

    @Provides
    fun bindConfigMapper(
        resources: Resources
    ): ConfigMapper<EnvironmentConfig, Config> {
        return ConfigMapperImpl(
            resources
        )
    }

    @Provides
    fun provideResources(
        @ApplicationContext context: Context
    ): Resources {
        return context.resources
    }

    @Provides
    fun provideAssets(
        @ApplicationContext context: Context
    ): AssetManager
    {
        return context.assets
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}