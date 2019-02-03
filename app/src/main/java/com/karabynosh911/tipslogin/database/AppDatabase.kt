package com.karabynosh911.tipslogin.database


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.karabynosh911.tipslogin.model.User
import com.karabynosh911.tipslogin.utils.DATABASE_NAME
import com.karabynosh911.tipslogin.utils.DATABASE_VERSIO

@Database(entities = arrayOf(User::class), version = DATABASE_VERSIO)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                .build()
    }
}