package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_empty_returnZeros(){
        // create active tasks

        //call your function
        val result= getActiveAndCompletedStats(emptyList())

        //check the result
        assertEquals(result.activeTasksPercent,0f)
        assertEquals(result.completedTasksPercent,0f)
    }

    @Test
    fun getActiveAndCompletedStats_null_returnZeros(){
        // create active tasks

        //call your function
        val result= getActiveAndCompletedStats(null)

        //check the result
        assertEquals(result.activeTasksPercent,0f)
        assertEquals(result.completedTasksPercent,0f)
    }

    @Test
    fun getActiveAndCompletedStats_both_returnFiftyFifty(){
        // create active tasks
        val tasks= listOf<Task>(
            Task("title","description",true),
            Task("title2","description2",false)
        )

        //call your function
        val result= getActiveAndCompletedStats(tasks)

        //check the result
        assertEquals(result.activeTasksPercent,50f)
        assertEquals(result.completedTasksPercent,50f)
    }

    @Test
    fun getActiveAndCompletedStats_noComplete_returnZeroHundred(){
        // create active tasks
        val tasks= listOf<Task>(
            Task("title","description",true),
            Task("title2","description2",true)
        )

        //call your function
        val result= getActiveAndCompletedStats(tasks)

        //check the result
        assertEquals(result.activeTasksPercent,0f)
        assertEquals(result.completedTasksPercent,100f)
    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnHundredZero(){
        // create active tasks
        val tasks= listOf<Task>(
            Task("title","description",false),
            Task("title2","description2",false)
        )

        //call your function
        val result= getActiveAndCompletedStats(tasks)

        //check the result
        assertEquals(result.activeTasksPercent,100f)
        assertEquals(result.completedTasksPercent,0f)
    }

}

