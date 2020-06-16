package com.awkris.hearsay.di

import android.app.Application
import androidx.room.Room
import com.awkris.hearsay.data.api.utils.ApiFactory
import com.awkris.hearsay.data.persistence.NewsDatabase
import com.awkris.hearsay.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault

@UnstableDefault
@Module
class ApplicationModule(private val application: Application) {
    @Provides
    fun provideApiGenerator() = ApiFactory()

    @Provides
    @ApplicationScope
    fun provideMovieDatabase(): NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            NewsDatabase.DB_NAME
        ).build()
    }
}