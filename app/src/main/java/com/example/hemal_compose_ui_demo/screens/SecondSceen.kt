package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hemal_compose_ui_demo.MyTopBar
import com.example.hemal_compose_ui_demo.models.ListItemModel
import com.example.hemal_compose_ui_demo.navigation.ScreenNavigation
import com.example.hemal_compose_ui_demo.ui.theme.HemalComposeUiDemoTheme
import com.example.hemal_compose_ui_demo.utils.MyAlertDialog

@Composable
fun SecondScreenView(fabName: String, navHostController: NavHostController) {
    val snackBarHostState = remember { SnackbarHostState() }
    val shouldShowDialog = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val isScrolling by remember {
        derivedStateOf { listState.isScrollInProgress }
    }
    val listItem = remember {
        listOf(
            ListItemModel(0, "https://dummyimage.com/300x200/000000/fff", "Title 1", "Subtitle 1"),
            ListItemModel(1, "https://dummyimage.com/300x200/012345/fff", "Title 2", "Subtitle 2"),
            ListItemModel(2, "https://dummyimage.com/300x200/123456/fff", "Title 3", "Subtitle 3"),
            ListItemModel(3, "https://dummyimage.com/300x200/654321/fff", "Title 4", "Subtitle 4"),
            ListItemModel(4, "https://dummyimage.com/300x200/112233/fff", "Title 5", "Subtitle 5"),
            ListItemModel(5, "https://dummyimage.com/300x200/141414/fff", "Title 6", "Subtitle 6"),
            ListItemModel(6, "https://dummyimage.com/300x200/414141/fff", "Title 7", "Subtitle 7"),
            ListItemModel(7, "https://dummyimage.com/300x200/123321/fff", "Title 8", "Subtitle 8"),
            ListItemModel(8, "https://dummyimage.com/300x200/321123/fff", "Title 9", "Subtitle 9"),
            ListItemModel(
                9, "https://dummyimage.com/300x200/666666/fff", "Title 10", "Subtitle 10"
            ),
            ListItemModel(
                10, "https://dummyimage.com/300x200/756877/fff", "Title 11", "Subtitle 11"
            ),
            ListItemModel(
                11, "https://dummyimage.com/300x200/897863/fff", "Title 12", "Subtitle 12"
            )
        )
    }

    HemalComposeUiDemoTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) },
            topBar = { MyTopBar(title = "Second Screen") },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(modifier = Modifier.padding(12.dp), state = listState) {
                    items(itemContent = { i ->
                        ItemView(listItem = listItem[i]) {
                            navHostController.navigate(ScreenNavigation.ThirdScreen)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }, count = listItem.size)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ExtendedFloatingActionButton(
                        onClick = { shouldShowDialog.value = true },
                        icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                        text = { Text(text = fabName) },
                        expanded = !isScrolling
                    )
                }
                if (shouldShowDialog.value) {
                    MyAlertDialog(shouldShowDialog = shouldShowDialog)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun SecondScreenPreview() {
    SecondScreenView(fabName = "dfs", navHostController = rememberNavController())
}