package com.awkris.hearsay.data.model

import com.awkris.hearsay.data.model.response.ArticleResponse
import java.text.SimpleDateFormat
import java.util.*

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
        private const val responseFormat = "yyyy-MM-dd"
        private const val outputFormat = "dd MMM yyyy"

        private fun formatTime(input: String): String {
            val formatter = SimpleDateFormat(responseFormat, Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val date = checkNotNull(formatter.parse(input))
            return SimpleDateFormat(outputFormat, Locale.getDefault()).format(date)
        }

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