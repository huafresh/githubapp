package com.hua.github_app.http.api

import com.hua.github_app.entity.Repository
import retrofit2.http.*

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
interface RepoService {
    @GET("user/repos")
    suspend fun getUserRepos(
        @Query("page") page: Int,
        @Query("type") type: String?,
        @Query("sort") sort: String?,
        @Query("direction") direction: String?
    ): List<Repository>
}