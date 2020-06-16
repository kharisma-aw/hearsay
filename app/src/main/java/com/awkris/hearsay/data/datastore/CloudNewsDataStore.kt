package com.awkris.hearsay.data.datastore

import com.awkris.hearsay.data.api.NewsApi
import com.awkris.hearsay.data.model.Article
import com.awkris.hearsay.data.model.Article.Companion.fromArticleResponse
import com.awkris.hearsay.data.model.PaginatedList
import io.reactivex.Single
import javax.inject.Inject

class CloudNewsDataStore @Inject constructor(private val newsApi: NewsApi) {
    fun getHeadlines(
        language: String,
        keyword: String?,
        page: Int?
    ): Single<PaginatedList<Article>> {
        return newsApi.getHeadlines(language, keyword, page).map {
            PaginatedList(
                it.articles.map(::fromArticleResponse),
                page ?: 1,
                it.totalResults ceilDivide DEFAULT_LIMIT
            )
        }
    }

    companion object {
        private const val DEFAULT_LIMIT = 20

        infix fun Int.ceilDivide(x: Int) = this / x + if (this % x != 0) 1 else 0
    }
}