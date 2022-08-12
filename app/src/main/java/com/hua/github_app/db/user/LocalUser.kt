package com.hua.github_app.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
@Entity(tableName = "user")
data class LocalUser(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var token: String? = null,
    var name: String? = null,
    var avatarUrl: String? = null,
)