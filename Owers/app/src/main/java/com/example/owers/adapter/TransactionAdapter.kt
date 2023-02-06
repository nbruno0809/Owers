package com.example.owers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.owers.data.Transaction
import com.example.owers.data.User
import com.example.owers.databinding.TransactionListItemBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val users = mutableListOf<User>()
    private val transactions = mutableListOf<Transaction>()

    inner class TransactionViewHolder(val binding: TransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionViewHolder(
            TransactionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val t = transactions[position]

        holder.binding.tvNameTo.text = findUserById(t.userIdFor)?.name ?: "User not found"
        holder.binding.tvNameFrom.text = findUserById(t.userIdPaid)?.name ?: "User not found"
        holder.binding.tvAmount.text = t.amount.toString()

    }


    fun addItem(item: Transaction) {
        transactions.add(item)
        notifyItemInserted(transactions.size - 1)
    }

    fun update(TransactionList: List<Transaction>, UserList: List<User>) {
        transactions.clear()
        users.clear()
        transactions.addAll(TransactionList)
        users.addAll(UserList)
        notifyDataSetChanged()
    }

    fun removeAll() {
        transactions.removeAll { true }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = transactions.size

    private fun findUserById(id: Long?): User? {
        if (id == null) {
            return null
        }
        return users.find { u -> u.id == id }
    }

    fun getUsers(): List<User> {
        return users
    }

}