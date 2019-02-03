package com.karabynosh911.tipslogin.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.karabynosh911.tipslogin.model.User
import com.karabynosh911.tipslogin.utils.USER_TABLE

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM $USER_TABLE")
    fun getUsersList(): List<User>

    @Query("DELETE FROM $USER_TABLE")
    fun deleteAllUsers()
}