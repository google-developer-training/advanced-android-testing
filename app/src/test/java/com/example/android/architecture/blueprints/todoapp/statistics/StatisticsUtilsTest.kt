package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
        val tasks = emptyList<Task>()

        val result = getActiveAndCompletedStats(tasks)

        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {

        // Create an active tasks
        val tasks = listOf(
            Task(title = "title", description = "desc", isCompleted = true)
        )

        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertThat(result.completedTasksPercent, `is`(100f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // Call your function
        val result = getActiveAndCompletedStats(null)

        // Check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {

        // Create an active tasks
        val tasks = listOf(
            Task(title = "title", description = "desc", isCompleted = false)
        )

        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertThat(result.completedTasksPercent, `is`(0f))
        assertThat(result.activeTasksPercent, `is`(100f))
    }

    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Create an active tasks
        val tasks = listOf(
            Task(title = "title", description = "desc", isCompleted = false),
            Task(title = "title", description = "desc", isCompleted = false),
            Task(title = "title", description = "desc", isCompleted = true),
            Task(title = "title", description = "desc", isCompleted = true),
            Task(title = "title", description = "desc", isCompleted = true)
        )

        // Call your function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertThat(result.completedTasksPercent, `is`(60f))
        assertThat(result.activeTasksPercent, `is`(40f))
    }
}