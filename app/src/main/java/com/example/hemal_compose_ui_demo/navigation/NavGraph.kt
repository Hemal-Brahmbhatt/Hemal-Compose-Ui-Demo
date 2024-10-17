package com.example.hemal_compose_ui_demo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.hemal_compose_ui_demo.screens.FirstScreenView
import com.example.hemal_compose_ui_demo.screens.SecondScreenView

@Composable
fun SetUpNavGraph(navController: NavHostController, startDestination: ScreenNavigation) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<ScreenNavigation.FirstScreen> { FirstScreenView(navController) }
        composable<ScreenNavigation.SecondScreen> { SecondScreenView(it.toRoute<ScreenNavigation.SecondScreen>().fabName) }
    }
}