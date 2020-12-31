package com.example.mvvmpractice

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoListAdapter: TodoListAdapter
    private lateinit var model: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity

            model = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
            todoListAdapter = TodoListAdapter(this@MainActivity, model)
            recyclerView.adapter = todoListAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            model.getAll().observe(this@MainActivity, Observer<List<ListData>> { todolist ->
                todoListAdapter.setTodoList(todolist)
            })

            addButton.setOnClickListener {
                model.showDialog(this@MainActivity, model)
            }
        }
    }
}