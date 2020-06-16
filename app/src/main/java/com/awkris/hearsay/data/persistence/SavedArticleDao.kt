package com.awkris.hearsay.data.persistence

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SavedArticleDao {
    @Insert
    fun insert(article: SavedArticle): Completable

    @Delete
    fun delete(vararg article: SavedArticle): Completable

    @Query("DELETE FROM `saved-article` WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("SELECT * FROM `saved-article`")
    fun getAllSavedArticle(): Single<List<SavedArticle>>
}