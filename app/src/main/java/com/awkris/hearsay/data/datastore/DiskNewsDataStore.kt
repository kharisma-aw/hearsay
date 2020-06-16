package com.awkris.hearsay.data.datastore

import com.awkris.hearsay.data.persistence.NewsDatabase
import com.awkris.hearsay.data.persistence.SavedArticle
import io.reactivex.Single

class DiskNewsDataStore(private val newsDb: NewsDatabase) {
    fun getSavedArticles(): Single<List<SavedArticle>> {
        return newsDb.savedArticleDao().getAllSavedArticle()
    }
}