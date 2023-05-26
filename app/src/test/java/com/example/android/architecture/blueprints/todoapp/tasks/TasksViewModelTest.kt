package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class TasksViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setNewTask() {

        // Assign a ViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Event<Unit>>{}

        try {

            // Act new Task is added
            tasksViewModel.newTaskEvent.observeForever(observer)
            tasksViewModel.addNewTask()

            // Assert adding a new Task
            val value = tasksViewModel.newTaskEvent.value
            Truth.assertThat(value?.getContentIfNotHandled()).isNotNull()

        } finally {
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
    }
}