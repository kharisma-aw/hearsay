package com.awkris.hearsay

import android.app.Application
import com.awkris.hearsay.di.ApiModule
import com.awkris.hearsay.di.ApplicationComponent
import com.awkris.hearsay.di.ApplicationModule
import com.awkris.hearsay.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import kotlinx.serialization.UnstableDefault
import timber.log.Timber

@UnstableDefault
class HearSay : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
        Timber.plant(Timber.DebugTree())
        if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun createAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .apiModule(ApiModule())
            .build()
    }

    companion object {
        @JvmStatic
        lateinit var appComponent: ApplicationComponent
            private set
    }
}