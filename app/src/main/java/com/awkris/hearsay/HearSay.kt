package com.awkris.hearsay

import android.app.Application
import com.awkris.hearsay.di.apiModule
import com.awkris.hearsay.di.dataStoreModule
import com.awkris.hearsay.di.repositoryModule
import com.awkris.hearsay.di.viewmodelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class HearSay : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
            Stetho.initializeWithDefaults(this)
        }

        startKoin {
            androidLogger()
            androidContext(this@HearSay)
            androidFileProperties()
            modules(
                listOf(apiModule, dataStoreModule, repositoryModule, viewmodelModule)
            )
        }
    }
}