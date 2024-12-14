package com.bignerdranch.android.todo.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.todo.R
import com.bignerdranch.android.todo.databinding.FragmentTaskBinding
import com.bignerdranch.android.todo.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

class TaskFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner
        binding.viewModel = viewModel

        adapter = TaskAdapter(TaskClickListener { task ->
            findNavController().navigate(TaskFragmentDirections.actionTaskFragmentToUpdateFragment(task))
        })

        viewModel.getAllTasks.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.apply {

            binding.recyclerView.adapter = adapter

            floatingActionButton.setOnClickListener{
                findNavController().navigate(R.id.action_taskFragment_to_addFragment)
            }
        }
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = adapter.currentList[position]
                viewModel.delete(task)

            /*    Snackbar.make(binding.root, "Deleted!", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.insert(task)
                    }
                    show()
                }*/
            }
        }).attachToRecyclerView(binding.recyclerView)
        //setHasOptionsMenu(true)
        return binding.root
    }
/*
    fun OnCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_menu, menu)
    }

    fun OnOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.action_delete_all -> deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete All")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes"){ dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }*/
}