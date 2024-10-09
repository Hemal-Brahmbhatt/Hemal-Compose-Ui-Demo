package com.example.hemal_compose_ui_demo.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hemal_compose_ui_demo.MyTopBar
import com.example.hemal_compose_ui_demo.ui.theme.HemalComposeUiDemoTheme
import com.example.hemal_compose_ui_demo.utils.MaterialSpinner
import com.example.hemal_compose_ui_demo.utils.MyData
import com.example.hemal_compose_ui_demo.utils.SpinnerSample
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, device = "id:pixel_2")
@Composable
fun FirstScreenView() {
    var isPasswordVisible by remember { mutableStateOf(false) }
    var genderSelected by remember { mutableStateOf("male") }
    var isChecked by remember { mutableStateOf(false) }
    var isRememberChecked by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    var nameString by remember { mutableStateOf("") }
    var pwdString by remember { mutableStateOf("") }
    val scrollerState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val mainLayoutWeight = 8f
    val checkBoxWeight = 0.5f
    val buttonWeight = 1f
    val listOfCity = listOf(
        MyData(0, "Ahmedabad"),
        MyData(1, "Surat"),
        MyData(2, "Rajkot"),
        MyData(3, "Mumbai"),
        MyData(4, "Delhi"),
        MyData(5, "Kolkata")
    )
    var selectedCity by remember { mutableStateOf(listOfCity.first()) }

    HemalComposeUiDemoTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) },
            topBar = { MyTopBar(title = "First Screen") },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .scrollable(
                            state = scrollerState, orientation = Orientation.Vertical
                        )
                        .weight(mainLayoutWeight),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                        value = nameString,
                        onValueChange = { nameString = it },
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        label = {
                            Text(
                                text = "Name"
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Username"
                            )
                        })

                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                        value = pwdString,
                        onValueChange = { pwdString = it },
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        label = {
                            Text(
                                text = "Password"
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Password"
                            )
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Filled.Favorite
                                    else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        })

                    MaterialSpinner(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                        title = "City",
                        options = listOfCity.map { it.name },
                        onSelect = {})

                    SpinnerSample(modifier = Modifier.padding(12.dp),
                        list = listOfCity,
                        preselected = selectedCity,
                        onSelectionChanged = { selectedCity = it })


                    Row(modifier = Modifier.fillMaxWidth()) {
                        val maleString = "Male"
                        val femaleString = "Female"
                        GenderRadioButton(
                            genderText = maleString, genderSelected = genderSelected
                        ) {
                            genderSelected = maleString
                        }
                        GenderRadioButton(
                            genderText = femaleString, genderSelected = genderSelected
                        ) {
                            genderSelected = femaleString
                        }
                    }

                    RememberMeSwitch(isOn = isRememberChecked) {
                        isRememberChecked = it
                    }
                    OutlinedButton(onClick = {}) {
                        Text(text = "Click Me")
                    }
                }
                TermsAndConditionsSection(modifier = Modifier.weight(checkBoxWeight),
                    isChecked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    })

                Button(
                    enabled = isChecked, onClick = {
                        if (isChecked) {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    "Next screen shown", withDismissAction = true
                                )
                            }
                        } else {
                            Toast.makeText(context, "Please agree to the terms", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .weight(buttonWeight)
                ) {
                    Text(text = "Next Screen")
                }
            }
        }
    }
}

@Composable
fun TermsAndConditionsSection(
    modifier: Modifier, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Text(text = "Terms And Conditions",
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onCheckedChange(!isChecked) })
    }
}

@Composable
fun GenderRadioButton(genderText: String, genderSelected: String, onSelected: (() -> Unit)) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = genderText.equals(genderSelected, true), onClick = onSelected
        )
        Text(
            text = genderText, modifier = Modifier.clickable(onClick = onSelected)
        )
    }
}

@Composable
fun RememberMeSwitch(isOn: Boolean, onTurningOn: ((Boolean) -> Unit)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Remember Me", modifier = Modifier.clickable(onClick = { onTurningOn(!isOn) })
        )
        Spacer(modifier = Modifier.width(12.dp))
        Switch(checked = isOn, onCheckedChange = onTurningOn)
    }
}