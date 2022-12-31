package com.ft.retrofit_kotlin_coroutines_example.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ft.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.ft.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.ft.retrofit_kotlin_coroutines_example.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

