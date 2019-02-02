package com.karabynosh911.tipslogin.model

data class Place(
    val custom_description: Any?,
    val formatted_address: String,
    val location_id: Int,
    val name: String,
    val on_moderation: Int,
    val place_id: String
)