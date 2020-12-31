package com.example.mvvmpractice

import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmpractice.databinding.TodoListBinding

class TodoListViewHolder(val binding : TodoListBinding, val model : MainViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ListData) {
        binding.apply{
            listdata = data
            if(data.label.equals("high"))
                label.setBackgroundResource(R.drawable.high)
            else if(data.label.equals("medium"))
                label.setBackgroundResource(R.drawable.medium)
            else if(data.label.equals("low"))
                label.setBackgroundResource(R.drawable.low)

            deleteButton.setOnClickListener{
                model.delete(data)
            }
        }
    }
}