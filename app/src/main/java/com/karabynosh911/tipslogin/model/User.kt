package com.karabynosh911.tipslogin.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.karabynosh911.tipslogin.utils.USER_TABLE

@Entity(tableName = USER_TABLE)
data class User(
    val api_key: String,
    val avatar: String,
//    val location_type: Any?,
    val name: String,
    val phone_code: String,
    val phone_number: String,
//    val place: Place,
//    val referral: Any?,
    val referral_bonus: Int,
    val second_name: String,
    val solar_staff: Boolean,
//    val userBalance: UserBalance,
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val user_id: Int

)