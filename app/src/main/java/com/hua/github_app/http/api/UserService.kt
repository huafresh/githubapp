package com.hua.github_app.http.api

import com.hua.github_app.http.entity.UserInfo
import retrofit2.http.*

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
interface UserService {
    @GET("user")
    suspend fun getPersonInfo(): UserInfo
}