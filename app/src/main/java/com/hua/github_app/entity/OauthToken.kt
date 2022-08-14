package com.hua.github_app.entity

import com.google.gson.annotations.SerializedName

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
data class OauthToken(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("scope")
    val scope: String
)