package com.awkris.hearsay.data.datastore

import com.awkris.hearsay.data.api.NewsApi
import com.awkris.hearsay.data.model.PaginatedList
import com.awkris.hearsay.data.model.response.ArticleResponse
import io.reactivex.Single

class CloudNewsDataStore(private val newsApi: NewsApi) {
    fun getHeadlines(
        language: String,
        keyword: String?,
        page: Int?
    ): Single<PaginatedList<ArticleResponse>> {
        return newsApi.getHeadlines(language, keyword, page).map {
            PaginatedList(
                it.articles,
                page ?: 1,
                it.totalResults ceilDivide DEFAULT_LIMIT
            )
        }
    }

    fun getDefaultLimit() = DEFAULT_LIMIT

    companion object {
        private const val DEFAULT_LIMIT = 20

        infix fun Int.ceilDivide(x: Int) = this / x + if (this % x != 0) 1 else 0
    }
}