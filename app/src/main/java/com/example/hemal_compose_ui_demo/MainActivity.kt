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
import androidx.compose.ui.tooling.preview.Preview
import com.example.hemal_compose_ui_demo.screens.FirstScreenView
import com.example.hemal_compose_ui_demo.screens.SecondScreenView
import com.example.hemal_compose_ui_demo.ui.theme.ToolbarBg

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPreview()
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun MyPreview(){
    SecondScreenView()
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