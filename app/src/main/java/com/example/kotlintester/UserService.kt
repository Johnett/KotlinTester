package com.example.kotlintester

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService{

    @Headers("Content-type: application/json")
    @POST("users")
    fun addUser(@Body body: UserParameter): Flowable<User>

}
