package com.bignerdranch.android.todo.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.todo.R
import com.bignerdranch.android.todo.databinding.FragmentAddBinding
import com.bignerdranch.android.todo.database.Task
import com.bignerdranch.android.todo.viewmodel.TaskViewModel

class AddFragment : Fragment() {
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddBinding.inflate(inflater)
        binding.apply {
            btnAdd.setOnClickListener {
                if (TextUtils.isEmpty(editTask.text)) {
                    Toast.makeText(requireContext(), "It's empty", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val title_str = editTask.text.toString()
                // Получаем выбранный приоритет из RadioGroup
                val selectedPriority = when (radioPriority.checkedRadioButtonId) {
                    R.id.btn_high -> 1
                    R.id.btn_medium -> 2
                    R.id.btn_low -> 3
                    else -> 0 // или можно назначить значение по умолчанию
                }
                // Создаем задачу с выбранным приоритетом
                val task = Task(
                    id = 0,
                    title = title_str,
                    priority = selectedPriority,
                    timestamp = System.currentTimeMillis()
                )
                // Сохраняем задачу
                viewModel.insert(task)
                Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_taskFragment)
            }
        }
        return binding.root
    }
}