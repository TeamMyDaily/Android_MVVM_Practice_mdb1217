package com.example.mvvmpractice

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        todoListAdapter = TodoListAdapter(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply{
            lifecycleOwner = this@MainActivity
            activity = this@MainActivity

            recyclerView.adapter = todoListAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            model = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
            model.getAll().observe(this@MainActivity, Observer<List<ListData>> { todolist ->
                todoListAdapter.setTodoList(todolist)
            })

            addButton.setOnClickListener {
                showDialog()
            }
        }
    }

    fun showDialog() {
        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        var dialogTitle = dialog.findViewById<EditText>(R.id.title)
        var dialogContent = dialog.findViewById<EditText>(R.id.content)
        val label= dialog.findViewById<Button>(R.id.label)
        val label2= dialog.findViewById<Button>(R.id.label2)
        val label3= dialog.findViewById<Button>(R.id.label3)
        val yesBtn = dialog.findViewById<Button>(R.id.yesBtn)
        val noBtn = dialog.findViewById<Button>(R.id.noBtn)
        var click = false
        var label_text : String = ""

        label.setOnClickListener {
            label.setBackgroundResource(R.drawable.shadow_high)
            label2.setBackgroundResource(R.drawable.medium)
            label3.setBackgroundResource(R.drawable.low)
            click = true
            label_text = "high"
        }
        label2.setOnClickListener {
            label.setBackgroundResource(R.drawable.high)
            label2.setBackgroundResource(R.drawable.shadow_medium)
            label3.setBackgroundResource(R.drawable.low)
            click = true
            label_text = "medium"
        }
        label3.setOnClickListener {
            label.setBackgroundResource(R.drawable.high)
            label2.setBackgroundResource(R.drawable.medium)
            label3.setBackgroundResource(R.drawable.shadow_low)
            click = true
            label_text = "low"
        }

        yesBtn.setOnClickListener {
            if(dialogTitle.text.toString().isEmpty() || dialogContent.text.toString().isEmpty())
                Toast.makeText(this, "빈칸이 있습니다", Toast.LENGTH_SHORT).show()
            else {
                if (click == false) {
                    Toast.makeText(this, "중요도를 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
                    model = ViewModelProvider(this@MainActivity).get(MainViewModel::class.java)
                    val listData = ListData(null, dialogTitle.text.toString(), dialogContent.text.toString(), label_text)
                    model.insert(listData)
                    dialog.dismiss()
                }
            }
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}