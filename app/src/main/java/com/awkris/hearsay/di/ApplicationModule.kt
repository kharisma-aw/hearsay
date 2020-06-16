package com.awkris.hearsay.di

import android.app.Application
import androidx.room.Room
import com.awkris.hearsay.data.api.utils.ApiFactory
import com.awkris.hearsay.data.persistence.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.UnstableDefault
import javax.inject.Singleton

@UnstableDefault
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideApiGenerator() = ApiFactory()

    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            NewsDatabase.DB_NAME
        ).build()
    }
}