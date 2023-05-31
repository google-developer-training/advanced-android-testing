package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.source.IDefaultTasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase

object ServiceLocator {

    private var database: ToDoDatabase? = null

    @Volatile
    private var tasksRepository: IDefaultTasksRepository? = null
}