package com.hua.github_app.http.entity

import com.google.gson.annotations.SerializedName
import java.util.*
/**
 * Created on 2022/8/10.
 *
 * @author hua
 */
class Repository {
    var id = 0
    var name: String? = null

    @SerializedName("full_name")
    var fullName: String? = null

    @SerializedName("private")
    var isRepPrivate = false

    @SerializedName("html_url")
    var htmlUrl: String? = null
    var description: String? = null
    var language: String? = null
    var owner: UserInfo? = null

    @SerializedName("default_branch")
    var defaultBranch: String? = null

    @SerializedName("created_at")
    var createdAt: Date? = null

    @SerializedName("updated_at")
    var updatedAt: Date? = null

    @SerializedName("pushed_at")
    var pushedAt: Date? = null

    @SerializedName("git_url")
    var gitUrl: String? = null

    @SerializedName("ssh_url")
    var sshUrl: String? = null

    @SerializedName("clone_url")
    var cloneUrl: String? = null

    @SerializedName("svn_url")
    var svnUrl: String? = null
    var size: Long = 0

    @SerializedName("stargazers_count")
    var stargazersCount = 0

    @SerializedName("watchers_count")
    var watchersCount = 0

    @SerializedName("forks_count")
    var forksCount = 0

    @SerializedName("open_issues_count")
    var openIssuesCount = 0

    @SerializedName("subscribers_count")
    var subscribersCount = 0
    var isFork = false
    var parent: Repository? = null

    @SerializedName("has_issues")
    var isHasIssues = false

    @SerializedName("has_projects")
    var isHasProjects = false

    @SerializedName("has_downloads")
    var isHasDownloads = false

    @SerializedName("has_wiki")
    var isHasWiki = false

    @SerializedName("has_pages")
    var isHasPages = false
    var sinceStargazersCount = 0
}