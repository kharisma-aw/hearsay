package com.awkris.hearsay.di

import com.awkris.hearsay.data.api.NewsApi
import com.awkris.hearsay.data.api.utils.ApiFactory
import com.awkris.hearsay.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault

@UnstableDefault
@Module
class ApiModule {
    @Provides
    @ApplicationScope
    fun provideNewsApi(apiFactory: ApiFactory): NewsApi {
        return apiFactory.createApi(NewsApi::class.java)
    }
}