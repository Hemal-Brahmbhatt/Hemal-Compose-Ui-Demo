package com.example.hemal_compose_ui_demo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.hemal_compose_ui_demo.R
import com.example.hemal_compose_ui_demo.models.ListItemModel

@Composable
fun ItemView(listItem: ListItemModel, onItemClick: ((list: ListItemModel) -> Unit)) {
    val painter = rememberAsyncImagePainter(
        model = listItem.imageUrl,
        error = painterResource(id = R.drawable.ic_launcher_background),
        placeholder = painterResource(id = R.drawable.ic_launcher_background)
    )
    Card(onClick = { onItemClick.invoke(listItem) }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = "item",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column {
                Text(
                    text = listItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 21.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp)
                )
                Text(
                    text = listItem.subtitle,
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemViewPreview() {
    ItemView(listItem = ListItemModel(
        0, "https://dummyimage.com/300x200/000/fff", "asfasf", "asfasf"
    ), onItemClick = {

    })
}