package com.awkris.hearsay.data.repository

import com.awkris.hearsay.data.datastore.CloudNewsDataStore
import com.awkris.hearsay.data.datastore.DiskNewsDataStore
import com.awkris.hearsay.data.model.Article
import com.awkris.hearsay.data.model.PaginatedList
import com.awkris.hearsay.data.persistence.SavedArticle
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val cloudDataStore: CloudNewsDataStore,
    private val diskDataStore: DiskNewsDataStore
) {
    fun getHeadlines(
        language: String,
        keyword: String?,
        page: Int?
    ): Single<PaginatedList<Article>> {
        return cloudDataStore.getHeadlines(language, keyword, page).map {
            PaginatedList(
                it.list.map { articleResponse -> Article.fromArticleResponse(articleResponse) },
                it.page,
                it.totalPage
            )
        }
    }

    fun getSavedArticles(): Single<List<SavedArticle>> {
        return diskDataStore.getSavedArticles()
    }

    fun getName() = this.javaClass.simpleName
}