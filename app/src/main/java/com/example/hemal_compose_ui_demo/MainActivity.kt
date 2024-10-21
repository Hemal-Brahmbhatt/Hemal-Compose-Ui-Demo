package com.example.hemal_compose_ui_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.hemal_compose_ui_demo.navigation.ScreenNavigation
import com.example.hemal_compose_ui_demo.navigation.SetUpNavGraph
import com.example.hemal_compose_ui_demo.ui.theme.ToolbarBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SetUpNavGraph(
                navController = navController,
                startDestination = ScreenNavigation.FirstScreen
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title:String){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ToolbarBg,
            titleContentColor = Color.White,
        ),
        title = {
            Text(text = title)
        }
    )
}