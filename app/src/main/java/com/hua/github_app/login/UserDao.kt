package com.hua.github_app.login

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
@Dao
interface UserDao {

    @Insert
    fun insert(user: LocalUser): Long

    @Query("SELECT * FROM user where id=:id")
    fun getUser(id: Int): LocalUser?

    @Delete
    fun delete(user: LocalUser): Int
}