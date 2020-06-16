package com.awkris.hearsay.data.api

import com.awkris.hearsay.data.api.utils.ApiConstants
import com.awkris.hearsay.data.model.response.HeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(ApiConstants.HEADLINES)
    fun getHeadlines(@Query("language") lang: String,
                     @Query("q") keyword: String?,
                     @Query("page") page: Int?
    ): Single<HeadlinesResponse>
}