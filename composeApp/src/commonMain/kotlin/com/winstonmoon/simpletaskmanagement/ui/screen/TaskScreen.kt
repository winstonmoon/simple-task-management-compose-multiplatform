package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Serializable
data object TaskRoute

@Composable
fun TaskRoute(
    modifier: Modifier = Modifier,
) {
    TaskScreen(
        modifier = modifier,
    )
}

@Composable
internal fun TaskScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text("hello world")
    }
}

@Preview
@Composable
fun PreviewTaskScreen() {
    SimpleTaskManagementTheme {
        TaskScreen()
    }
}