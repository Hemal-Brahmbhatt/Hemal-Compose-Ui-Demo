package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hemal_compose_ui_demo.models.UserInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoBottomSheet(userInfo: UserInfo,sheetState:SheetState,modifier: Modifier=Modifier,onDismissSheet:(()->Unit)) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissSheet,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top row: Close button and title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "User Info",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDismissSheet) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }

            // Divider
            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // User info
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Name: ${userInfo.name}", fontSize = 16.sp)
                Text(text = "City: ${userInfo.city} ", fontSize = 16.sp)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun UserInfoBottomSheetPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Top row: Close button and title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "User Info",
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
        }

        // Divider
        HorizontalDivider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // User info
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(text = "Name: ", fontSize = 16.sp)
            Text(text = "City:  ", fontSize = 16.sp)
        }
    }
}
