package com.gauravgoyal.mvvm_with_testing.features.userinfo.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.gauravgoyal.mvvm_with_testing.application.di.scope.PerApplication
import com.gauravgoyal.mvvm_with_testing.features.userinfo.model.User
import javax.inject.Inject

/**
 * The Room database that contains the Users table
 */
@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UsersDatabase? = null

        @Inject
        fun getInstance(@PerApplication context: Context): UsersDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        @Inject
        fun buildDatabase(@PerApplication context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        UsersDatabase::class.java, "Sample.db")
                        .build()
    }
}
