package com.awkris.hearsay.data.model

data class PaginatedList<out T>(
    val list: List<T>,
    val page: Int,
    val totalPage: Int
)