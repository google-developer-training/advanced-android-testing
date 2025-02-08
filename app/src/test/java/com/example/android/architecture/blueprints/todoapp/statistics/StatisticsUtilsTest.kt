package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest{

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // Create an active task
        val task = Task("My test task","Im testing with JUnit4")
        val tasks = listOf<Task>(task)
        // Call your function
        val statResults = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(statResults.activeTasksPercent, `is`(100f))
        assertThat(statResults.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_oneTaskCompleted_noActiveTasks_returnsZeroHundred() {
        // Create an active task
        val task = Task("My test task","one task completed and no active tasks",true)
        val tasks = listOf<Task>(task)
        // Call your function
        val statResults = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(statResults.activeTasksPercent, `is`(0f))
        assertThat(statResults.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_twoTasksCompleted_threeActiveTasks_returnsZeroHundred() {
        // Create an active task
        val task1 = Task("My test task","Two completed tasks and three active tasks",true)
        val task2 = Task("My test task","Two completed tasks and three active tasks",true)
        val task3 = Task("My test task","Two completed tasks and three active tasks")
        val task4 = Task("My test task","Two completed tasks and three active tasks")
        val task5 = Task("My test task","Two completed tasks and three active tasks")

        val tasks = listOf<Task>(task1,task2,task3,task4,task5)
        // Call your function
        val statResults = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(statResults.activeTasksPercent, `is`(60f))
        assertThat(statResults.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStats_null_returnsZeroZero() {
        // Create an active task
        val tasks = listOf<Task>()
        // Call your function
        val statResults = getActiveAndCompletedStats(tasks)
        // Check the result
        assertThat(statResults.activeTasksPercent, `is`(0f))
        assertThat(statResults.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeroZero() {
        // Call your function
        val statResults = getActiveAndCompletedStats(emptyList())
        // Check the result
        assertThat(statResults.activeTasksPercent, `is`(0f))
        assertThat(statResults.completedTasksPercent, `is`(0f))
    }
}