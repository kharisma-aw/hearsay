package com.awkris.hearsay.presentation.headlines

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.awkris.hearsay.data.model.Article
import com.awkris.hearsay.data.model.NetworkState
import com.awkris.hearsay.data.model.PaginatedList
import com.awkris.hearsay.data.repository.NewsRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.util.*

class HeadlinesDataSource(
    private val repository: NewsRepository
) : PageKeyedDataSource<Int, Article>() {
    var networkState = MutableLiveData<NetworkState>()
        private set

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        networkState.postValue(NetworkState.Loading)
        repository.getHeadlines(Locale.getDefault().language, null, null).subscribe(
            object : SingleObserver<PaginatedList<Article>> {
                override fun onSuccess(t: PaginatedList<Article>) {
                    networkState.postValue(NetworkState.Success)
                    callback.onResult(
                        t.list,
                        null,
                        if (t.totalPage > 1) t.page + 1 else null
                    )
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.Error(e.message))
                }

            }
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        networkState.postValue(NetworkState.Loading)
        repository.getHeadlines(Locale.getDefault().language, null, params.key).subscribe(
            object : SingleObserver<PaginatedList<Article>> {
                override fun onSuccess(t: PaginatedList<Article>) {
                    networkState.postValue(NetworkState.Success)
                    callback.onResult(t.list, if (t.page < t.totalPage) t.page + 1 else null)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.Error(e.message))
                }

            }
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        //Unused
    }
}

class HeadlinesDataSourceFactory(
    private val headlinesDataSource: HeadlinesDataSource
) : DataSource.Factory<Int, Article>() {
    private val dataSource = MutableLiveData<HeadlinesDataSource>()

    override fun create(): DataSource<Int, Article> {
        dataSource.postValue(headlinesDataSource)
        return headlinesDataSource
    }

    fun getDataSource() = dataSource
}