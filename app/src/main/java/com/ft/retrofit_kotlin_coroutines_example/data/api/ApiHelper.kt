package com.ft.retrofit_kotlin_coroutines_example.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers(page: Int) = apiService.getUsers(page = page)
}