package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.Event
import com.example.android.architecture.blueprints.todoapp.EventObserver
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTaskRepository
import getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith

class TasksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setUp() {
        val fakeTaskRepository = FakeTaskRepository().apply {
            val task1 = Task("Title1", "Desc1")
            val task2 = Task("Title2", "Desc2", true)
            val task3 = Task("Title3", "Desc3", true)
            addTasks(task1, task2, task3)
        }
        tasksViewModel = TasksViewModel(fakeTaskRepository)
    }

    @Test
    fun `add new task sets new event in 'newTaskEvent'`() {
        tasksViewModel.addNewTask()
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), notNullValue())
    }

    @Test
    fun `set filter 'ALL_TASKS' sets 'tasksAddViewVisible' to be true`() {
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value, `is`(true))
    }
}