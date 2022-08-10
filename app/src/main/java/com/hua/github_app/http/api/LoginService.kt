package com.hua.github_app.http.api

import com.hua.github_app.http.entity.OauthToken
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
interface LoginService {
//    @POST("authorizations")
//    @Headers("Accept: application/json")
//    fun authorizations(
//        @NonNull @Body authRequestModel: AuthRequestModel?
//    ): Observable<Response<BasicToken?>?>?

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String?,
        @Query("client_secret") clientSecret: String?,
        @Query("code") code: String?,
        @Query("state") state: String?
    ): OauthToken
}