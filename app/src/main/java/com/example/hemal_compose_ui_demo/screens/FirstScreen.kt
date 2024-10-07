package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hemal_compose_ui_demo.MyTopBar
import com.example.hemal_compose_ui_demo.ui.theme.HemalComposeUiDemoTheme

@Preview
@Composable
fun FirstScreenView(){
    HemalComposeUiDemoTheme {
        Scaffold(topBar = {
            MyTopBar(title = "FirstScreen")
        }, modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {

            }
        }
    }
}