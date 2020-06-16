package com.awkris.hearsay.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedArticle::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun savedArticleDao(): SavedArticleDao

    companion object {
        const val DB_NAME = "news.db"
    }
}