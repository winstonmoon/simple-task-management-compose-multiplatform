package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.input_task_screen_title

@Serializable
data object InputTaskRoute

@Composable
fun InputTaskRoute(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    InputTaskScreen(
        modifier = modifier,
        onClickBack = onClickBack,
    )
}

@Composable
internal fun InputTaskScreen(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                title = Res.string.input_task_screen_title,
                onClickBack = onClickBack,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .addFocusCleaner(focusManager),
        ) {
            var text by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }

            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Task") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                })
            )

            Text(
                text = "Status"
            )

            DropdownMenu(
                modifier = Modifier
                    .wrapContentSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Ready") },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = { Text(text = "In Progress") },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = { Text(text = "Done") },
                    onClick = {}
                )
            }

            Text(
                text = "priority"
            )

            DropdownMenu(
                modifier = Modifier
                    .wrapContentSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Low") },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = { Text(text = "Medium") },
                    onClick = {}
                )
                DropdownMenuItem(
                    text = { Text(text = "High") },
                    onClick = {}
                )
            }
        }
    }
}

private fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}