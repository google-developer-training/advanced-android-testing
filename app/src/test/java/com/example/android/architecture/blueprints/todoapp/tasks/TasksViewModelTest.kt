package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class TasksViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupBefore() {

        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setNewTask() {

        // Assign a ViewModel
        // Done in setupBefore

        // Act new Task is added
        tasksViewModel.addNewTask()

        // Assert adding a new Task
        val value = tasksViewModel.newTaskEvent.getOrWaitValue()
        Truth.assertThat(value.getContentIfNotHandled()).isNotNull()
    }

    @Test
    fun setFiltering_toAllTask_taskAddNewVisible() {

        // Assign
        // Done in setupBefore

        // Act
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Assert
        val value = tasksViewModel.tasksAddViewVisible.getOrWaitValue()
        Truth.assertThat(value).isTrue()
    }
}