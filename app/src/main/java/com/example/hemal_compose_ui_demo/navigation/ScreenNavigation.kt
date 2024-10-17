package com.example.hemal_compose_ui_demo.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNavigation {

    @Serializable
    data object FirstScreen : ScreenNavigation()

    @Serializable
    data class SecondScreen(val fabName: String) : ScreenNavigation()
}