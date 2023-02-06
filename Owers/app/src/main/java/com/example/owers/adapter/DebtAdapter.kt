package com.example.owers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.owers.TransactionCreator
import com.example.owers.data.Transaction
import com.example.owers.data.User
import com.example.owers.databinding.TransactionListItemBinding

class DebtAdapter(val fm: FragmentManager) : RecyclerView.Adapter<DebtAdapter.DebtViewHolder>() {

    private val users = mutableListOf<User>()
    private val settlements = mutableListOf<Transaction>()

    inner class DebtViewHolder(val binding: TransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DebtViewHolder(
            TransactionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        val t = settlements[position]

        holder.binding.tvNameTo.text = findUserById(t.userIdFor)?.name ?: "User not found"
        holder.binding.tvNameFrom.text = findUserById(t.userIdPaid)?.name ?: "User not found"
        holder.binding.tvAmount.text = t.amount.toString()
        holder.binding.ivIcon.setOnClickListener {
            TransactionCreator.createNewTransaction(t)
            settlements.remove(t)
            notifyDataSetChanged()
        }

    }

    fun update(TransactionList: List<Transaction>, UserList: List<User>) {
        settlements.clear()
        users.clear()
        settlements.addAll(TransactionList)
        users.addAll(UserList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = settlements.size

    private fun findUserById(id: Long?): User? {
        if (id == null) {
            return null
        }
        return users.find { u -> u.id == id }
    }


}