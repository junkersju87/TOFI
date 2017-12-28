package com.example.evg.tofi.api

import com.example.evg.tofi.models.Count
import com.example.evg.tofi.models.EventModel
import com.example.evg.tofi.models.UserDetails
import com.example.evg.tofi.models.UserModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ServerApiService{

    @POST("v1/auth/login")
    @FormUrlEncoded
    fun getToken(@Field("username")username: String,
                 @Field("password")password:String): Single<UserDetails>

    @POST("v1/auth/registration")
    @FormUrlEncoded
    fun getRegistration(@Field("login")login:String,
            @Field("password")password:String,
                 @Field("fio")fio:String,
                 @Field("bd")bd : String,
                 @Field("email")emial : String,
                 @Field("phone")phone : String):
            Single<UserModel>

    @POST("/v1/user/profile")
    @FormUrlEncoded
    fun getUser(@Header("Authorization")token:String, @Field("phone")phone:String): Single<UserModel>

    @GET("/v1/ads/favorites")
    fun getFavorites(@Header("Authorization")token : String) :Single<List<EventModel>>

    @POST("/v1/pay/pay")
    @FormUrlEncoded
    fun pay(@Header("Authorization")token : String,@Field("hash")hash:String) : Single<Count>

    companion object {
        fun create(): ServerApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://94.130.9.56:667/")
                    .build()

            return retrofit.create(ServerApiService::class.java)
        }
    }
}