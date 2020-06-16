package com.awkris.hearsay.di

import com.awkris.hearsay.data.api.NewsApi
import com.awkris.hearsay.data.api.utils.ApiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.UnstableDefault
import javax.inject.Singleton

@UnstableDefault
@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideNewsApi(apiFactory: ApiFactory): NewsApi {
        return apiFactory.createApi(NewsApi::class.java)
    }
}