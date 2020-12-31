package com.example.mvvmpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.databinding.TodoListBinding

class TodoListAdapter(private val context: Context) : RecyclerView.Adapter<TodoListViewHolder>() {
    private lateinit var binding: TodoListBinding
    var data = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.todo_list, parent, false)
        return TodoListViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    fun setTodoList(todolist: List<ListData>) {
        data = todolist.toMutableList()
        notifyDataSetChanged()
    }

}