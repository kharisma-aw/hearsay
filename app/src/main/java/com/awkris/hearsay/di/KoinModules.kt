package com.awkris.hearsay.di

import androidx.room.Room
import com.awkris.hearsay.data.api.NewsApi
import com.awkris.hearsay.data.api.utils.ApiFactory
import com.awkris.hearsay.data.datastore.CloudNewsDataStore
import com.awkris.hearsay.data.datastore.DiskNewsDataStore
import com.awkris.hearsay.data.persistence.NewsDatabase
import com.awkris.hearsay.data.repository.NewsRepository
import com.awkris.hearsay.presentation.headlines.HeadlinesDataSource
import com.awkris.hearsay.presentation.headlines.HeadlinesDataSourceFactory
import com.awkris.hearsay.presentation.headlines.HeadlinesViewModel
import kotlinx.serialization.UnstableDefault
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    factory { HeadlinesDataSourceFactory(get()) }
    factory { HeadlinesDataSource(get()) }

    viewModel { HeadlinesViewModel(get()) }
}

@UnstableDefault
val apiModule = module {
    fun provideApiFactory() = ApiFactory()
    single { provideApiFactory().createApi(NewsApi::class.java) }
}

val dataStoreModule = module {
    single {
        Room.databaseBuilder(
            get(),
            NewsDatabase::class.java,
            NewsDatabase.DB_NAME
        ).build()
    }
    single { CloudNewsDataStore(get()) }
    single { DiskNewsDataStore(get()) }
}

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}