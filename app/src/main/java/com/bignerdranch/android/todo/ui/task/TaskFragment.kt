package com.bignerdranch.android.todo.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.todo.R
import com.bignerdranch.android.todo.databinding.FragmentTaskBinding
import com.bignerdranch.android.todo.viewmodel.TaskViewModel

class TaskFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTaskBinding.inflate(inflater)

        binding.apply {
            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_taskFragment_to_addFragment)
            }
        }
        return binding.root
    }
}