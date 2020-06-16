package com.awkris.hearsay.presentation.headlines

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.awkris.hearsay.data.model.Article
import com.awkris.hearsay.data.model.NetworkState
import com.awkris.hearsay.di.RepositoryInterface
import dagger.hilt.EntryPoints

class HeadlinesViewModel @ViewModelInject constructor(
    dataSourceFactory: HeadlinesDataSourceFactory
) : ViewModel() {
    val networkState: LiveData<NetworkState>
    val headlines: LiveData<PagedList<Article>>

    init {
        networkState = Transformations.switchMap(dataSourceFactory.getDataSource()) { dataSource ->
            dataSource.networkState
        }

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        headlines = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

    fun getRepositoryName(context: Context): String {
        val repoInterface = EntryPoints.get(context, RepositoryInterface::class.java)
        return repoInterface.getRepository().getName()
    }

//    fun refresh() {
//        dataSourceFactory.recreate()
//    }
}