package com.example.android.architecture.blueprints.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailViewModel
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val tasksRepository: TasksRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(tasksRepository)
                    isAssignableFrom(TaskDetailViewModel::class.java) -> TaskDetailViewModel(tasksRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}