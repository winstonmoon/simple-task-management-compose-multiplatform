package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.floating_action_button_label_register
import simpletaskmanagement.composeapp.generated.resources.input_task_screen_title

@Serializable
data object InputTaskRoute

@Composable
fun InputTaskRoute(
    onClickBack: () -> Unit,
    onClickRegisterButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InputTaskViewModel = koinViewModel<InputTaskViewModel>(),
) {
    val title by viewModel.title.collectAsStateWithLifecycle()
    val status by viewModel.status.collectAsStateWithLifecycle()
    val priority by viewModel.priority.collectAsStateWithLifecycle()
    val date by viewModel.date.collectAsStateWithLifecycle()

    InputTaskScreen(
        modifier = modifier,
        title = title,
        status = status,
        priority = priority,
        date = date,
        onClickRegisterButton = onClickRegisterButton,
        onClickBack = onClickBack,
        onClickStatus = {
            viewModel.setStatus(it)
        },
        onClickPriority = {
            viewModel.setPriority(it)
        },
        onDateSelected = {
            viewModel.setDate(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InputTaskScreen(
    title: String,
    status: String,
    priority: String,
    date: Long,
    onClickBack: () -> Unit,
    onClickRegisterButton: () -> Unit,
    onClickStatus: (String) -> Unit,
    onClickPriority: (String) -> Unit,
    onDateSelected: (Long?) -> Unit,
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
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier,
                onClick = onClickRegisterButton,
                title = Res.string.floating_action_button_label_register,
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
            var statusExpanded by remember { mutableStateOf(false) }
            var priorityExpanded by remember { mutableStateOf(false) }
            var shouldShowDatePicker by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()
            val formattedDate = remember(date) {
                val dateFormat = SimpleDateFormat("YYYY/MM/dd", Locale.getDefault())
                dateFormat.format(Date(date))
            }

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

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        statusExpanded = true
                    }
                ) {
                    Text(text = "status:$status")
                }

                DropdownMenu(
                    modifier = Modifier
                        .wrapContentSize(),
                    expanded = statusExpanded,
                    onDismissRequest = { statusExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Ready") },
                        onClick = { onClickStatus("Ready") }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "In Progress") },
                        onClick = { onClickStatus("In Progress") }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Done") },
                        onClick = { onClickStatus("Done") }
                    )
                }
            }

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        priorityExpanded = true
                    }
                ) {
                    Text(text = "priority:$priority")
                }

                DropdownMenu(
                    modifier = Modifier
                        .wrapContentSize(),
                    expanded = priorityExpanded,
                    onDismissRequest = { priorityExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Low") },
                        onClick = { onClickPriority("Low") }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Medium") },
                        onClick = { onClickPriority("Medium") }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "High") },
                        onClick = { onClickPriority("High") }
                    )
                }
            }

            // TODO
            Button(
                onClick = {
                    shouldShowDatePicker = true
                }
            ) {
                Text(text = "date:$date")
            }

            if (shouldShowDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { shouldShowDatePicker = false },
//                onDismissRequest = onDismiss,
                    confirmButton = {
                        TextButton(onClick = {
                            onDateSelected(datePickerState.selectedDateMillis)
                            shouldShowDatePicker = false
//                        onDismiss()
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {  shouldShowDatePicker = false }) {
//                    TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
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