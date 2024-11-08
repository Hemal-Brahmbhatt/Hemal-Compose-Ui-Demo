package com.example.hemal_compose_ui_demo.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hemal_compose_ui_demo.MyTopBar
import com.example.hemal_compose_ui_demo.ui.theme.HemalComposeUiDemoTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FifthScreenView(
    navController: NavHostController
) {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current

    HemalComposeUiDemoTheme {
        Scaffold(
            topBar = { MyTopBar(title = "Fifth Screen") }, modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if(event == Lifecycle.Event.ON_START) {
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionsState.permissions.forEach { perm ->
                        when (perm.permission) {
                            Manifest.permission.CAMERA -> {
                                when {
                                    perm.status.isGranted -> {
                                        Text(text = "Camera permission accepted")
                                    }

                                    perm.status.shouldShowRationale -> {
                                        Text(
                                            text = "Camera permission is needed" +
                                                    "to access the camera"
                                        )
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(
                                            text = "Camera permission was permanently" +
                                                    "denied. You can enable it in the app" +
                                                    "settings."
                                        )
                                    }
                                }
                            }

                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    perm.status.isGranted -> {
                                        Text(text = "Record audio permission accepted")
                                    }

                                    perm.status.shouldShowRationale -> {
                                        Text(
                                            text = "Record audio permission is needed" +
                                                    "to access the camera"
                                        )
                                    }

                                    perm.isPermanentlyDenied() -> {
                                        Text(
                                            text = "Record audio permission was permanently" +
                                                    "denied. You can enable it in the app" +
                                                    "settings."
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !status.shouldShowRationale && !this.status.isGranted
}

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun FifthViewPreview() {
    FifthScreenView(navController = rememberNavController())
}