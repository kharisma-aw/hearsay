package com.awkris.hearsay.data.utils

import java.text.SimpleDateFormat
import java.util.*

private const val responseFormat = "yyyy-MM-dd"
private const val outputFormat = "dd MMM yyyy"

fun formatTime(input: String): String {
    val formatter = SimpleDateFormat(responseFormat, Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val date = checkNotNull(formatter.parse(input))
    return SimpleDateFormat(outputFormat, Locale.getDefault()).format(date)
}