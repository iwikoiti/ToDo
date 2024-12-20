package com.bignerdranch.android.todo.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.todo.R
import com.bignerdranch.android.todo.database.Task
import com.bignerdranch.android.todo.databinding.FragmentUpdateBinding
import com.bignerdranch.android.todo.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUpdateBinding.inflate(inflater)
        val args = UpdateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            updateEditTask.setText(args.task.title)
            updateEditDesc.setText(args.task.description)
            var priority = args.task.priority


            when(priority){
                1 -> updateRadioPriority.check(R.id.update_btn_high)
                2 -> updateRadioPriority.check(R.id.update_btn_medium)
                3 -> updateRadioPriority.check(R.id.update_btn_low)
                else -> updateRadioPriority.clearCheck()
            }

            btnUpdate.setOnClickListener{
                if(TextUtils.isEmpty(updateEditTask.text)){
                    Toast.makeText(requireContext(), "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val task_str = updateEditTask.text.toString()
                val desc_str = updateEditDesc.text.toString()
                val selectedPriority = when (updateRadioPriority.checkedRadioButtonId) {
                    R.id.update_btn_high -> 1
                    R.id.update_btn_medium -> 2
                    R.id.update_btn_low -> 3
                    else -> 0
                }

                val task = Task(
                    args.task.id,
                    task_str,
                    desc_str,
                    selectedPriority,
                    args.task.timestamp
                )

                viewModel.update(task)
                Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_taskFragment)
            }
        }

        return binding.root
    }
}