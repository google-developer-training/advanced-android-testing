package com.example.android.architecture.blueprints.todoapp

import android.content.Context
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.source.DefaultTasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource

object ServiceLocator {

    private var db: ToDoDatabase? = null

    @Volatile
    var tasksRepository: TasksRepository? = null

    fun provideTasksRepository(context: Context): TasksRepository {
        return tasksRepository ?: synchronized(this) {
            tasksRepository ?: createTasksRepository(context)
        }
    }

    private fun createTasksRepository(context: Context): TasksRepository =
            DefaultTasksRepository(createTaskLocalDataSource(context), TasksRemoteDataSource).also {
                tasksRepository = it
            }

    private fun createTaskLocalDataSource(context: Context): TasksDataSource {
        val db = db ?: createDatabase(context)
        return TasksLocalDataSource(db.taskDao())
    }

    private fun createDatabase(context: Context): ToDoDatabase {
        val result = Room.databaseBuilder(context,
                ToDoDatabase::class.java, "Tasks.db")
                .build()
        db = result
        return result
    }
}