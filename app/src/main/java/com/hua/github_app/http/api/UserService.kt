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

//    @NonNull
//    @GET("users/{user}")
//    fun getUser(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?
//    ): Observable<Response<User?>?>?
//
//    @NonNull
//    @GET("user/following/{user}")
//    fun checkFollowing(
//        @Path("user") user: String?
//    ): Observable<Response<ResponseBody?>?>?
//
//    /**
//     * Check if one user follows another
//     */
//    @NonNull
//    @GET("users/{user}/following/{targetUser}")
//    fun checkFollowing(
//        @Path("user") user: String?,
//        @Path("targetUser") targetUser: String?
//    ): Observable<Response<ResponseBody?>?>?
//
//    @NonNull
//    @PUT("user/following/{user}")
//    fun followUser(
//        @Path("user") user: String?
//    ): Observable<Response<ResponseBody?>?>?
//
//    @NonNull
//    @DELETE("user/following/{user}")
//    fun unfollowUser(
//        @Path("user") user: String?
//    ): Observable<Response<ResponseBody?>?>?
//
//    @NonNull
//    @GET("users/{user}/followers")
//    fun getFollowers(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<User?>?>?>?
//
//    @NonNull
//    @GET("users/{user}/following")
//    fun getFollowing(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<User?>?>?>?
//
//    /**
//     * List events performed by a user
//     */
//    @NonNull
//    @GET("users/{user}/events")
//    fun getUserEvents(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<Event?>?>?>?
//
//    /**
//     * List github public events
//     */
//    @NonNull
//    @GET("events")
//    fun getPublicEvent(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<Event?>?>?>?
//
//    @NonNull
//    @GET("users/{user}/received_events")
//    fun getNewsEvent(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<Event?>?>?>?
//
//    @NonNull
//    @GET("orgs/{org}/members")
//    fun getOrgMembers(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("org") org: String?,
//        @Query("page") page: Int
//    ): Observable<Response<ArrayList<User?>?>?>?
//
//    @NonNull
//    @GET("users/{user}/orgs")
//    fun getUserOrgs(
//        @Header("forceNetWork") forceNetWork: Boolean,
//        @Path("user") user: String?
//    ): Observable<Response<ArrayList<User?>?>?>?
}