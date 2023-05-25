package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.google.common.truth.Truth
import org.junit.Test


internal class StatisticsUtilsTest {


    @Test
    fun getActiveAndCompletedStats_noCompletedTasks_returnsHundredZero() {

        // Assign
        val tasks =
            listOf<Task>(Task(title = "First Task", description = "desc", isCompleted = false))

        // Act
        val statsResult = getActiveAndCompletedStats(tasks)

        // Assert
        Truth.assertThat(statsResult.completedTasksPercent).isZero()
        Truth.assertThat(statsResult.activeTasksPercent).isEqualTo(100f)
    }

    @Test
    fun getActiveAndCompletedStats_noActiveTasks_returnCompletedHundredActiveZero() {

        // Assign
        val tasks =
            listOf<Task>(Task(title = "First Task", description = "desc", isCompleted = true))

        // Act
        val statsResult = getActiveAndCompletedStats(tasks)

        // Assert
        Truth.assertThat(statsResult.completedTasksPercent).isEqualTo(100f)
        Truth.assertThat(statsResult.activeTasksPercent).isZero()
    }

    @Test
    fun getActiveAndCompletedStats_twoActiveThreeCompleted_returnFourtySixty() {

        // Assign
        val tasks = listOf<Task>(
            Task(title = "First Task", description = "desc", isCompleted = true),
            Task(title = "First Task", description = "desc", isCompleted = true),
            Task(title = "First Task", description = "desc", isCompleted = false),
            Task(title = "First Task", description = "desc", isCompleted = false),
            Task(title = "First Task", description = "desc", isCompleted = false)
        )

        // Act
        val statsResult = getActiveAndCompletedStats(tasks)

        // Assert
        Truth.assertThat(statsResult.completedTasksPercent).isEqualTo(40f)
        Truth.assertThat(statsResult.activeTasksPercent).isEqualTo(60f)
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnZero() {

        // Assign
        val tasks = emptyList<Task>()

        // Act
        val statsResult = getActiveAndCompletedStats(tasks)

        // Assert
        Truth.assertThat(statsResult.completedTasksPercent).isZero()
        Truth.assertThat(statsResult.activeTasksPercent).isZero()
    }

    @Test
    fun getActiveAndCompletedStats_nullList_returnZero() {

        // Assign
        val tasks = null

        // Act
        val statsResult = getActiveAndCompletedStats(tasks)

        // Assert
        Truth.assertThat(statsResult.completedTasksPercent).isZero()
        Truth.assertThat(statsResult.activeTasksPercent).isZero()
    }
}