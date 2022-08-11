package com.hua.github_app.http.api

import android.database.Observable
import com.hua.github_app.http.entity.Repository
import com.hua.github_app.http.entity.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
interface SearchService {

    @GET("search/repositories")
    fun searchRepos(
        @Query(value = "q", encoded = true) query: String?,
        @Query("sort") sort: String?,
        @Query("order") order: String?,
        @Query("page") page: Int
    ): SearchResult<Repository>
}