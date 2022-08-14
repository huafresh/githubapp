package com.hua.github_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hua.github_app.db.user.LocalUser
import com.hua.github_app.db.user.UserDao

/**
 * Created on 2022/8/11.
 *
 * @author hua
 */
@Database(entities = [LocalUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "github-app.db"
        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DB_NAME
                ).build()
            }
            return instance!!
        }
    }
}