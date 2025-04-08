package com.example.tuan4mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuan4mvvm.model.Task
import android.graphics.Color

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    // Sample color array for task items
    private val colors = arrayOf(
        Color.parseColor("#B3E5FC"), // Light blue
        Color.parseColor("#FFCDD2"), // Light red
        Color.parseColor("#E6EE9C"), // Light yellow
        Color.parseColor("#FFCDD2")  // Light red (repeated for example)
    )

    init {
        // Initialize with sample data
        val sampleTasks = listOf(
            Task(1, "Complete Android Project", "Finish the UI, integrate API, and write documentation", colors[0]),
            Task(2, "Complete Android Project", "Finish the UI, integrate API, and write documentation", colors[1]),
            Task(3, "Complete Android Project", "Finish the UI, integrate API, and write documentation", colors[2]),
            Task(4, "Complete Android Project", "Finish the UI, integrate API, and write documentation", colors[3])
        )
        _tasks.value = sampleTasks
    }

    fun addTask(title: String, description: String) {
        val currentList = _tasks.value ?: emptyList()
        val newId = (currentList.maxByOrNull { it.id }?.id ?: 0) + 1
        val colorIndex = newId % colors.size
        val newTask = Task(newId, title, description, colors[colorIndex])

        _tasks.value = currentList + newTask
    }
}