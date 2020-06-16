package com.awkris.hearsay.data.model

import com.awkris.hearsay.data.model.response.ArticleResponse
import com.awkris.hearsay.data.utils.formatTime

data class Article(
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
        fun fromArticleResponse(response: ArticleResponse): Article {
            return with(response) {
                Article(
                    author,
                    content,
                    description,
                    formatTime(publishedAt),
                    source.name,
                    title,
                    url,
                    urlToImage
                )
            }
        }
    }
}