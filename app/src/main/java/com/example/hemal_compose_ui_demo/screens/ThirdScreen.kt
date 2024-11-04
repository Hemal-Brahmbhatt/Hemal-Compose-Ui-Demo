package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hemal_compose_ui_demo.MyTopBar
import com.example.hemal_compose_ui_demo.navigation.ScreenNavigation
import com.example.hemal_compose_ui_demo.ui.theme.HemalComposeUiDemoTheme
import kotlinx.coroutines.launch

@Composable
fun ThirdScreenView(
    navController: NavHostController, drawerValue: DrawerValue = DrawerValue.Closed
) {
    val drawerState = rememberDrawerState(initialValue = drawerValue)
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    val pagerState2 = rememberPagerState(pageCount = {
        10
    })
    val colorList = remember {
        mutableListOf(
            Color.Red,
            Color.Cyan,
            Color.Blue,
            Color.Magenta,
            Color.Yellow,
            Color.Green,
            Color.Red,
            Color.Cyan,
            Color.Blue,
            Color.Magenta,
            Color.Green
        )
    }
    val scope = rememberCoroutineScope()
    HemalComposeUiDemoTheme {
        ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Drawer title", modifier = Modifier.padding(16.dp))
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear, contentDescription = "Close Drawer"
                        )
                    }
                }
                HorizontalDivider()
                NavigationDrawerItem(label = { Text(text = "First Screen") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(ScreenNavigation.FirstScreen) {
                            popUpTo(ScreenNavigation.FirstScreen) {
                                inclusive = true
                            }
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Second Screen") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(ScreenNavigation.SecondScreen("123123")) {
                            popUpTo(ScreenNavigation.FirstScreen)
                        }
                    })
                NavigationDrawerItem(label = { Text(text = "Third Screen") },
                    selected = true,
                    onClick = {
                        scope.launch { drawerState.close() }
                    })
            }
        }) {
            Scaffold(
                topBar = {
                    MyTopBar(title = "Third Screen", isDrawerEnable = true, onDrawerClicked = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
                }, modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    HorizontalPager(state = pagerState) { page ->
                        Card(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Page: ${page + 1}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(colorList[page + 1]),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    VerticalPager(state = pagerState2) { page ->
                        Card(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Page: ${page + 1}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .background(colorList[page + 1]),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    }
}

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun ThirdViewPreview() {
    ThirdScreenView(navController = rememberNavController(), drawerValue = DrawerValue.Closed)
}