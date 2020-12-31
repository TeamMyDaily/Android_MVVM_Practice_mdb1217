package com.example.mvvmpractice

import android.app.Application
import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(application:Application) : AndroidViewModel(application) {
    val repository = ListRepository(application)
    val lists = repository.getAll()

    fun getAll(): LiveData<List<ListData>> {
        return lists
    }

    fun insert(l : ListData) {
        repository.insert(l)
    }

    fun update(l : ListData) {
        repository.update(l)
    }

    fun delete(l : ListData) {
        repository.delete(l)
    }

    fun showDialog(activity : MainActivity, model : MainViewModel) {
        val dialog = Dialog(activity)
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
                Toast.makeText(activity, "빈칸이 있습니다", Toast.LENGTH_SHORT).show()
            else {
                if (click == false) {
                    Toast.makeText(activity, "중요도를 선택해주세요", Toast.LENGTH_SHORT).show()
                } else {
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