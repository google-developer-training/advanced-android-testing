package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*

class StatisticsUtilsTest {

    @Test
    fun `0 completed 1 active task return 0% and 100%`() {
        val tasks = listOf(Task("title", "desc", false))
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun `1 completed 0 active task return 100% and 0%`() {
        val tasks = listOf(Task("title", "desc", true))
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun `2 completed 3 active task return 40% and 60%`() {
        val activeTask = Task("title", "desc", false)
        val completedTask = Task("title", "desc", true)
        val tasks = listOf(completedTask, completedTask, activeTask, activeTask, activeTask)
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent, `is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun `null task list return 0% and 0%`() {
        val result = getActiveAndCompletedStats(null)
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun `empty task list return 0% and 0%`() {
        val tasks = emptyList<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}