package com.ft.retrofit_kotlin_coroutines_example.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ft.retrofit_kotlin_coroutines_example.data.model.Users
import com.ft.retrofit_kotlin_coroutines_example.databinding.ItemLayoutBinding
import com.ft.retrofit_kotlin_coroutines_example.ui.main.adapter.MainAdapter.DataViewHolder

class MainAdapter(private var users: ArrayList<Users>) : RecyclerView.Adapter<DataViewHolder>() {

    inner class DataViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            binding.apply {
                textViewUserName.text = user.data[adapterPosition].firstName
                textViewUserEmail.text = user.data[adapterPosition].email
                Glide.with(imageViewAvatar.context).load(user.data[adapterPosition].avatar).into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder = DataViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Users>) {
        this.users = users as ArrayList<Users>
    }
}