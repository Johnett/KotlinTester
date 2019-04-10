package com.example.kotlintester

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NetworkModule {

    interface UserService{
        @Headers("Content-type: application/json")
        @POST("users")
        fun addUser(@Body body: UserParameter): Flowable<User>
    }

    companion object {
        fun create(): NetworkModule {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://backender.herokuapp.com/")
                    .build()

            return retrofit.create(NetworkModule::class.java)
        }
    }

}