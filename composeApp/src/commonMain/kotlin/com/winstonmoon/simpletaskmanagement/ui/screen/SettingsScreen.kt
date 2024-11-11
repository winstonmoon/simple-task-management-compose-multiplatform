package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
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
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            General()
            MoreOptions()
        }
    }
}

private fun LazyListScope.General(

) {
    item {
        Title("")
    }
    // TODO
//    items(General) {
//
//    }
    item {
        HorizontalDivider(
            thickness = 1.dp
        )
    }
}

private fun LazyListScope.MoreOptions(

) {
    item {
        Title("")
    }
    // TODO
//    items(General) {
//
//    }
    item {
        HorizontalDivider(
            thickness = 1.dp
        )
    }
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(all = 16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun ListItem(
    configTitle: String,
    selectedConfig: String,
    // TODO
    selectableConfig: List<String>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .clickable {
                onClick()
            },
    ) {
        Text(
            text = configTitle,
            // TODO: change color
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = selectedConfig,
            // TODO: change color
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}