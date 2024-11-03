package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.settings_title

@Serializable
data object SettingsRoute

@Composable
fun SettingsRoute(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SettingsScreen(
        modifier = modifier,
        onClickBack = onClickBack
    )
}

@Composable
internal fun SettingsScreen(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                title = Res.string.settings_title,
                onClickBack = onClickBack,
            )
        },
    ) { paddingValues ->

        val scrollState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            state = scrollState,
        ) {
            items(20) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clickable {

                        },
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Theme",
                    )
                    Text(
                        text = "system default"
                    )
                }
                HorizontalDivider()
            }
        }
    }
}