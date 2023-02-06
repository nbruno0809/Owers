package com.example.owers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.owers.adapter.DebtAdapter
import com.example.owers.data.Transaction
import com.example.owers.data.owersDatabase
import com.example.owers.databinding.FragmentDebtsBinding
import kotlin.concurrent.thread
import kotlin.math.abs


class debts : Fragment() {

    private lateinit var adapter: DebtAdapter
    private lateinit var binding: FragmentDebtsBinding
    private lateinit var db: owersDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDebtsBinding.inflate(inflater, container, false)
        db = MainActivity.getDB()
        initRecyclerView()
        return binding.root

    }

    private fun initRecyclerView() {
        adapter = DebtAdapter(this.parentFragmentManager)
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
        calculate()
    }
    
    private fun calculate() {
        thread {
            val users = MainActivity.getDB().userDao().getAll()
            val positives = users.filter { u -> u.balance >= 0 }.sortedBy { u -> -u.balance }
            val negatives = users.filter { u -> u.balance < 0 }.sortedBy { u -> u.balance }
            val settle: MutableList<Transaction> = mutableListOf()
            var i = 0
            for (p in positives) {
                while (p.balance > 0) {
                    val n = negatives[i]
                    if (p.balance + n.balance > 0) {
                        settle.add(Transaction(null, n.id!!, p.id!!, abs(n.balance)))
                        p.balance += n.balance
                        n.balance = 0
                        i++
                    } else {
                        settle.add(Transaction(null, n.id!!, p.id!!, abs(p.balance)))
                        n.balance += p.balance
                        p.balance = 0
                    }
                }
            }
            activity?.runOnUiThread {
                adapter.update(settle, users)
            }
        }
    }
}