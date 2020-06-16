package com.awkris.hearsay.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.awkris.hearsay.data.model.response.ArticleResponse

@Entity(
    tableName = "saved-article"
)
data class SavedArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String?
) {
    companion object {
        fun fromArticle(article: ArticleResponse): SavedArticle {
            return with(article) {
                SavedArticle(
                    author = author,
                    content = content,
                    description = description,
                    publishedAt= publishedAt,
                    source = source.name,
                    title = title,
                    url = url,
                    urlToImage = urlToImage
                )
            }
        }
    }
}