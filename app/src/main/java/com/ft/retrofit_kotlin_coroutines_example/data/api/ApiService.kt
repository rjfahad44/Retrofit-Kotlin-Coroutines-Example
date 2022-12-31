package com.ft.retrofit_kotlin_coroutines_example.data.api

import com.ft.retrofit_kotlin_coroutines_example.data.model.Users
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 2): List<Users>

}