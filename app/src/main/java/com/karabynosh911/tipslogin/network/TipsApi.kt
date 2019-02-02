package com.karabynosh911.tipslogin.network

import com.karabynosh911.tipslogin.model.Response
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface TipsApi {

    @POST("/login")
    fun login(@Query("phone_code") phoneCode: String,
              @Query("phone_number") phoneNumber:String,
              @Query("password") password:String):Observable<Response>
}