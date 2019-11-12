package com.example.android.architecture.blueprints.todoapp.taskdetail

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@MediumTest
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {

    @Test
    fun activeTaskDetails_DisplayedInUi() {
        val activeTask = Task("Active Task", "AndroidX rocks", false)

        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)
        sleep(3000)
    }
}