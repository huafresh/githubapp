package com.hua.github_app.http.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class User {
    enum class UserType {
        User, Organization
    }

    var login: String? = null
    var id: String? = null
    var name: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null
    var type: UserType? = null
    var company: String? = null
    var blog: String? = null
    var location: String? = null
    var email: String? = null
    var bio: String? = null

    @SerializedName("public_repos")
    var publicRepos = 0

    @SerializedName("public_gists")
    var publicGists = 0
    var followers = 0
    var following = 0

    @SerializedName("created_at")
    var createdAt: Date? = null

    @SerializedName("updated_at")
    var updatedAt: Date? = null

    override fun toString(): String {
        return "User(login=$login, id=$id, name=$name, avatarUrl=$avatarUrl, htmlUrl=$htmlUrl, type=$type, company=$company, blog=$blog, location=$location, email=$email, bio=$bio, publicRepos=$publicRepos, publicGists=$publicGists, followers=$followers, following=$following, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}