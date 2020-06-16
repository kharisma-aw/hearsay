package com.awkris.hearsay.data.api.utils

import timber.log.Timber

object ApiConstants {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val HEADLINES = "top-headlines"
}

fun log(t: Throwable) {
    Timber.d("Error encountered: ${t.message}\nCause: ${t.cause}")
}