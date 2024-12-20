package com.example.hemal_compose_ui_demo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.hemal_compose_ui_demo.screens.FifthScreenView
import com.example.hemal_compose_ui_demo.screens.FirstScreenView
import com.example.hemal_compose_ui_demo.screens.ForthScreenView
import com.example.hemal_compose_ui_demo.screens.SecondScreenView
import com.example.hemal_compose_ui_demo.screens.ThirdScreenView

@Composable
fun SetUpNavGraph(navController: NavHostController, startDestination: ScreenNavigation) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<ScreenNavigation.FirstScreen>(exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }) {
            FirstScreenView(navController = navController)
        }
        composable<ScreenNavigation.SecondScreen>(enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        }, exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }) {
            SecondScreenView(
                fabName = it.toRoute<ScreenNavigation.SecondScreen>().fabName,
                navHostController = navController
            )
        }

        composable<ScreenNavigation.ThirdScreen> { ThirdScreenView(navController = navController) }

        composable<ScreenNavigation.ForthScreen> { ForthScreenView(navController = navController) }

        composable<ScreenNavigation.FifthScreen> { FifthScreenView(navController = navController) }
    }
}