package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeDefaultTasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.IDefaultTasksRepository
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

 
internal class TasksViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var tasksRepository: FakeDefaultTasksRepository
    lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupBefore() {

        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository = FakeDefaultTasksRepository()
        tasksRepository.addTask(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
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