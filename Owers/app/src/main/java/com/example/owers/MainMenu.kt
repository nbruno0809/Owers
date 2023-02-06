package com.example.owers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.owers.databinding.FragmentMainMenuBinding

class MainMenu : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnD.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_depts)
        }

        binding.btnT.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_transactions)
        }

        binding.btnG.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_group)
        }
    }
}