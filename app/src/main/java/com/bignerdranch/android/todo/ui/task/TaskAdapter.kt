package com.bignerdranch.android.todo.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.todo.databinding.RowLayoutBinding
import com.bignerdranch.android.todo.database.Task

class TaskAdapter(val clickListener: TaskClickListener): ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback) {

    companion object TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }

    class ViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task, clickListener: TaskClickListener){
            binding.task = task
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }
}

class TaskClickListener(val clickListener: (task: Task) -> Unit){
    fun onClick(task: Task) = clickListener(task)
}