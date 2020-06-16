package com.awkris.hearsay.data.api.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("x-api-key", "3ce54f30949749c3a8c86b29c26955c9")
            .build()
        return chain.proceed(request)
    }
}