package com.example.mvvmpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val context: Context) : RecyclerView.Adapter<TodoListViewHolder>() {
    var data = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.todo_list, parent, false)
        return TodoListViewHolder(view)
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