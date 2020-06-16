package com.awkris.hearsay.data.datastore

import com.awkris.hearsay.data.persistence.NewsDatabase
import com.awkris.hearsay.data.persistence.SavedArticle
import io.reactivex.Single
import javax.inject.Inject

class DiskNewsDataStore @Inject constructor(private val newsDb: NewsDatabase) {
    fun getSavedArticles(): Single<List<SavedArticle>> {
        return newsDb.savedArticleDao().getAllSavedArticle()
    }
}