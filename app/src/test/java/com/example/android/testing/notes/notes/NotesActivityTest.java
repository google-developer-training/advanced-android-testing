package com.example.android.testing.notes.notes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import com.example.android.testing.notes.R;
import com.example.android.testing.notes.statistics.StatisticsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.util.ReflectionHelpers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;


/**
 * Robolectric tests for {@link NotesActivity}.
 */
@RunWith(RobolectricTestRunner.class)
public class NotesActivityTest {
    private NotesActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(NotesActivity.class);
    }

    @Test
    public void clickOnStatisticsNavigationItem_showsStatisticsScreen() {
        openDrawer();

        // Start statistics screen.
        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        MenuItem item = navigationView.getMenu().findItem(R.id.statistics_navigation_menu_item);

        NavigationView.OnNavigationItemSelectedListener listener =
                ReflectionHelpers.getField(navigationView, "mListener");
        listener.onNavigationItemSelected(item);

        // Check that statistics Activity was opened.
        Intent expectedIntent = new Intent(activity, StatisticsActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity().getComponent(),
                equalTo(expectedIntent.getComponent()));
    }

    @Test
    public void clickOnAndroidHomeIcon_opensNavigation() {
        DrawerLayout drawerLayout = openDrawer();
        assertThat(drawerLayout.isDrawerOpen(Gravity.START), is(true));
    }

    @NonNull
    private DrawerLayout openDrawer() {
        shadowOf(activity).clickMenuItem(android.R.id.home);
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();
        drawerLayout.computeScroll();
        return drawerLayout;
    }
}