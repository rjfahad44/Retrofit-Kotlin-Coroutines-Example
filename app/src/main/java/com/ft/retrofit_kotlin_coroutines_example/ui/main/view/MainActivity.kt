package com.ft.retrofit_kotlin_coroutines_example.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ft.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.ft.retrofit_kotlin_coroutines_example.data.api.RetrofitBuilder
import com.ft.retrofit_kotlin_coroutines_example.data.model.Users
import com.ft.retrofit_kotlin_coroutines_example.databinding.ActivityMainBinding
import com.ft.retrofit_kotlin_coroutines_example.ui.base.ViewModelFactory
import com.ft.retrofit_kotlin_coroutines_example.ui.main.adapter.MainAdapter
import com.ft.retrofit_kotlin_coroutines_example.ui.main.viewmodel.MainViewModel
import com.ft.retrofit_kotlin_coroutines_example.utils.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))[MainViewModel::class.java]
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers(page = 2).observe(this){
            it?.let { resource ->
               when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun retrieveList(users: List<Users>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
