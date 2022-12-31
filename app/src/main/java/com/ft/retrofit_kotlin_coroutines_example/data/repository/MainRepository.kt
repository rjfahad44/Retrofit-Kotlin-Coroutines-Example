package com.ft.retrofit_kotlin_coroutines_example.data.repository

import com.ft.retrofit_kotlin_coroutines_example.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers(page: Int) = apiHelper.getUsers(page = page)
}