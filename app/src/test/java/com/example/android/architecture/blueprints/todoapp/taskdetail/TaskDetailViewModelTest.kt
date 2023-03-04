package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TaskDetailViewModelTest{

    // Use a fake repository to be injected into the viewmodel
    private lateinit var tasksRepository: FakeTestRepository

    // Subject under test
    private lateinit var taskDetailViewModel: TaskDetailViewModel

    // id of a random task for test

    private lateinit var taskId : String

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        taskId = task1.id
        tasksRepository.addTasks(task1, task2, task3)

        taskDetailViewModel = TaskDetailViewModel(tasksRepository)
    }


    @Test
    fun editTask_setsNewEditTaskEvent(){

        taskDetailViewModel.editTask()

        val value = taskDetailViewModel.editTaskEvent.getOrAwaitValue()

        assertThat(value.getContentIfNotHandled(), (not(nullValue())))

    }

//    @Test
//    fun completeTask_setsNewCompletedTaskEvent()  {
//
//        val task = tasksRepository.tasksServiceData.toList()
//
//        val randomTask = task[0].second
//
//        taskDetailViewModel.start(randomTask.id)
//
//        // set task as completed
//
//        taskDetailViewModel.setCompleted(true)
//
//
//        assertThat(randomTask.isCompleted, `is` (true))
//
//    }

}