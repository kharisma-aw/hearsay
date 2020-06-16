package com.awkris.hearsay.di

import com.awkris.hearsay.di.scope.ApplicationScope
import com.awkris.hearsay.presentation.headlines.HeadlinesFragment
import dagger.Component
import kotlinx.serialization.UnstableDefault

@UnstableDefault
@ApplicationScope
@Component(modules = [
    ApplicationModule::class,
    ApiModule::class
])
interface ApplicationComponent {
    fun inject(fragment: HeadlinesFragment)
}