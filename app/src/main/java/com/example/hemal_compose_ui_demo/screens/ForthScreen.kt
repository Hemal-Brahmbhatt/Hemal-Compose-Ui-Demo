package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
fun ForthScreenView(
    navController: NavHostController
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val pagerState2 = rememberPagerState(pageCount = {
        10
    })
    val scope = rememberCoroutineScope()
    val tabs = listOf("Home", "About", "Settings")
    HemalComposeUiDemoTheme {
        Scaffold(
            topBar = { MyTopBar(title = "Forth Screen") }, modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage, divider = {
                        Spacer(modifier = Modifier.height(5.dp))
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    tabs.forEachIndexed { index, s ->
                        Tab(selected = pagerState.currentPage == index, onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }, text = {
                            Text(text = s)
                        })
                    }
                }
                TabContent(pagerState = pagerState, title = tabs[pagerState.currentPage], navController)
                ScrollableTabRow(
                    selectedTabIndex = pagerState2.currentPage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEachIndexed { index, s ->
                        Tab(selected = pagerState2.currentPage == index, onClick = {
                            scope.launch {
                                pagerState2.animateScrollToPage(index)
                            }
                        }, text = {
                            Text(text = s.toString())
                        })
                    }
                }
                TabContent(pagerState = pagerState2, title = "${pagerState2.currentPage + 1}", navController)
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun ForthViewPreview() {
    ForthScreenView(navController = rememberNavController())
}

@Composable
fun TabContent(pagerState: PagerState, title: String, navController: NavHostController) {
    HorizontalPager(state = pagerState) {
        Card(modifier = Modifier.padding(10.dp), onClick = {
            navController.navigate(ScreenNavigation.FifthScreen)
        }) {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}