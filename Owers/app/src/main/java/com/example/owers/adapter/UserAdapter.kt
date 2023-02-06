package com.example.owers.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.owers.R
import com.example.owers.data.User
import com.example.owers.databinding.UserListItemBinding
import com.google.android.material.snackbar.Snackbar

class UserAdapter(private val listener: UserListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val items = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = items[position]
        holder.binding.tvName.text = user.name
        holder.binding.tvBalance.text = user.balance.toString()
        if (user.balance >= 0) {
            holder.binding.tvBalance.setTextColor(Color.parseColor("#519C53"))
        } else {
            holder.binding.tvBalance.setTextColor(Color.parseColor("#9C5151"))
        }
        holder.binding.ibRemove.setOnClickListener {
            if (user.balance != 0) {
                Snackbar.make(holder.binding.root, R.string.cantDelete, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                listener.onItemRemoved(user)
            }
        }
    }

    fun removeItem(item: User) {
        var i = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(i)
    }

    fun addItem(item: User) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(UserList: List<User>) {
        items.clear()
        items.addAll(UserList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    interface UserListener {
        fun onItemRemoved(item: User)
    }

    inner class UserViewHolder(val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}