package com.example.android.architecture.blueprints.todoapp.tasks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTaskRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TasksFragmentTest {

    private lateinit var tasksRepository: TasksRepository

    @Before
    fun setUp() {
        tasksRepository = FakeTaskRepository()
        ServiceLocator.tasksRepository = tasksRepository
    }

    @After
    fun tearDown() {
        runBlockingTest {
            ServiceLocator.resetRepository()
        }
    }

    @Test
    fun clickTask_navigateToDetailFragmentOne() = runBlockingTest {
        val task1 = Task("Task1", "AndroidX rocks1", false)
        val task2 = Task("Task2", "AndroidX rocks2", true)
        val task3 = Task("Task3", "AndroidX rocks3", false)
        tasksRepository.saveTask(task1)
        tasksRepository.saveTask(task2)
        tasksRepository.saveTask(task3)

        val scenario = launchFragmentInContainer<TasksFragment>(Bundle(), R.style.AppTheme)

        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onView(withId(R.id.tasks_list))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                        hasDescendant(withText("Task1")), click())
                )
        verify(navController)
                .navigate(TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment(
                        task1.id)
                )
    }

    @Test
    fun clickAddTaskButton_navigateToAddEditFragment() {
        val scenario = launchFragmentInContainer<TasksFragment>(Bundle(), R.style.AppTheme)
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onView(withId(R.id.add_task_fab)).perform(click())
        verify(navController)
                .navigate(TasksFragmentDirections.actionTasksFragmentToAddEditTaskFragment(
                        null, getApplicationContext<Context>().getString(R.string.add_task))
                )
    }
}