package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1","Description1")
    private val task2 = Task("Title2","Description2")
    private val task3 = Task("Title3","Description3")
    private val remoteTask = listOf(task1, task2).sortedBy { it.id }
    private val localTask = listOf(task3).sortedBy { it.id }
    private val newTask = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: FakeDataSource
    private lateinit var tasksLocalDataSource: FakeDataSource
    private lateinit var defaultDataSource: DefaultTasksRepository

    @Before
    fun createRepository() {

        tasksRemoteDataSource = FakeDataSource(remoteTask.toMutableList())
        tasksLocalDataSource = FakeDataSource(remoteTask.toMutableList())
        defaultDataSource = DefaultTasksRepository(tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined)
    }

    @Test
    fun getTasks_requestAllTaskFromRemoteDataSource_returnsRemoteTask() = runBlockingTest {
        // Assign
        val forceUpdate = true

        // Act
        val tasks = defaultDataSource.getTasks(forceUpdate) as Result.Success

        // Assert
        Truth.assertThat(tasks.data).isEqualTo(remoteTask)

    }

    @Test
    fun saveTask() {
    }

    @Test
    fun deleteAllTasks() {
    }
}