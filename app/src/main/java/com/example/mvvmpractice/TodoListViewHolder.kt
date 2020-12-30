package com.example.mvvmpractice

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.title);
    private val content: TextView = itemView.findViewById(R.id.content);
    private val label: TextView = itemView.findViewById(R.id.label)

    fun onBind(data: ListData) {
        title.text = data.title
        content.text = data.content
        label.text = data.label
        if(label.text.equals("high"))
            label.setBackgroundResource(R.drawable.high)
        else if(label.text.equals("medium"))
            label.setBackgroundResource(R.drawable.medium)
        else if(label.text.equals("low"))
            label.setBackgroundResource(R.drawable.low)
    }
}