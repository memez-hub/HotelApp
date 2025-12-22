package com.example.hotelapp.navGraphs

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hotelapp.BottomNavItem
import com.example.hotelapp.ui.dashboard.DashboardScreen
import com.example.hotelapp.ui.schedule.ScheduleScreen

@Composable
fun HomeNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onHotelCardClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(
            BottomNavItem.Home.route,

            //transitions
            exitTransition = { NavTransitions.slideOutToLeft() },
            popEnterTransition = { NavTransitions.slideInFromLeft() }
        )
        {
            DashboardScreen(
                paddingValues = paddingValues,
                onHotelCardClick = onHotelCardClick)
        }
        composable(BottomNavItem.Schedule.route) {
            ScheduleScreen(paddingValues = paddingValues)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
object NavTransitions {

    private const val ANIMATION_DURATION = 300

    fun slideInFromRight(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(ANIMATION_DURATION)
        )

    fun slideOutToLeft(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(ANIMATION_DURATION)
        )

    fun slideInFromLeft(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(ANIMATION_DURATION)
        )

    fun slideOutToRight(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(ANIMATION_DURATION)
        )

    fun fadeIn(): EnterTransition =
        fadeIn(animationSpec = tween(ANIMATION_DURATION))

    fun fadeOut(): ExitTransition =
        fadeOut(animationSpec = tween(ANIMATION_DURATION))
}

