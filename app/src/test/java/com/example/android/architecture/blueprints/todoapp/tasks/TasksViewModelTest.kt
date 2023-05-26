package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(AndroidJUnit4::class)
internal class TasksViewModelTest {

    @Test
    fun addNewTask_setNewTask() {

        // Assign a ViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // Assert adding a new Task
        tasksViewModel.addNewTask()

        // Act new Task is added
        // TODO: Test live data.
    }
}