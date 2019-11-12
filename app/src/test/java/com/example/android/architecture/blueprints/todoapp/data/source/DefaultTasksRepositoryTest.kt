package com.example.android.architecture.blueprints.todoapp.data.source

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var remoteFakeDataSource: FakeDataSource
    private lateinit var localFakeDataSource: FakeDataSource
    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun createRepository() {
        remoteFakeDataSource = FakeDataSource(remoteTasks.toMutableList())
        localFakeDataSource = FakeDataSource(localTasks.toMutableList())
        tasksRepository = DefaultTasksRepository(remoteFakeDataSource, localFakeDataSource, Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get tasks from remote`() = runBlockingTest {
            val tasks = tasksRepository.getTasks(true) as Result.Success
            assertThat(tasks.data, `is`(remoteTasks))
    }

}