package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable

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
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                title = "InputTask",
                onClickBack = onClickBack,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {

        }
    }
}