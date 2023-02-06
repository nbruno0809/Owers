package com.example.owers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.owers.adapter.UserAdapter
import com.example.owers.data.User
import com.example.owers.data.owersDatabase
import com.example.owers.databinding.FragmentGroupBinding
import kotlin.concurrent.thread

class group : Fragment(), UserAdapter.UserListener {

    private lateinit var binding: FragmentGroupBinding
    private lateinit var db: owersDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupBinding.inflate(inflater, container, false)
        db = MainActivity.getDB()
        binding.fabAdd.setOnClickListener {
            NewUserDialog().show(parentFragmentManager, NewUserDialog.TAG)
        }
        initRecyclerView()
        return binding.root

    }

    private fun initRecyclerView() {
        adapter = UserAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = db.userDao().getAll()
            activity?.runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemRemoved(item: User) {
        thread {
            db.userDao().deleteItem(item)
            val items = db.userDao().getAll()
            activity?.runOnUiThread {
                adapter.removeItem(item)
            }
        }
    }


    companion object {
        private lateinit var adapter: UserAdapter

        fun getUserAdapter(): UserAdapter {
            return adapter
        }
    }


}
