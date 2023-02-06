package com.example.owers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.owers.TransactionCreator.db
import com.example.owers.adapter.TransactionAdapter
import com.example.owers.data.Transaction
import com.example.owers.databinding.FragmentTransactionsBinding
import kotlin.concurrent.thread

class transactions : Fragment(), NewTransactionDialog.NewTransactionDialogListener {

    private lateinit var binding: FragmentTransactionsBinding
    private lateinit var adapter: TransactionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        binding.fabAdd.setOnClickListener {
            NewTransactionDialog(this, adapter.getUsers()).show(
                parentFragmentManager,
                NewTransactionDialog.TAG
            )
        }
        binding.fabDelete.setOnClickListener {
            TransactionCreator.removeAllTransaction()
            adapter.removeAll()
        }
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = TransactionAdapter()
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val users = db.userDao().getAll()
            val transactions = db.transDao().getAll()
            activity?.runOnUiThread {
                adapter.update(transactions, users)
            }
        }
    }

    override fun onTransactionCreated(newT: Transaction) {
        adapter.addItem(newT)
    }
}



