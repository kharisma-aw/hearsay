package com.awkris.hearsay.di

import com.awkris.hearsay.data.repository.NewsRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface RepositoryInterface {
    fun getRepository(): NewsRepository
}