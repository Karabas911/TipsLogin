package com.karabynosh911.tipslogin.model

data class User(
    val api_key: String,
    val avatar: String,
    val location_type: Any?,
    val name: String,
    val phone_code: String,
    val phone_number: String,
    val place: Place,
    val referral: Any?,
    val referral_bonus: Int,
    val second_name: String,
    val solar_staff: Boolean,
    val userBalance: UserBalance,
    val user_id: Int
)