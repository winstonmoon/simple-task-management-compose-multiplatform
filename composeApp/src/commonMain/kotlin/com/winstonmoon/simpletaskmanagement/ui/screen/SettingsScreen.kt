package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomModalBottomSheet
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.settings_screen_title
import simpletaskmanagement.composeapp.generated.resources.settings_screen_general
import simpletaskmanagement.composeapp.generated.resources.settings_screen_more_options

sealed interface Settings {
    val title: String
    val configs: List<String>
}

data class Theme(
    override val title: String = "Theme",
    override val configs: List<String> = listOf("Dark", "Light", "Follow System"),
) : Settings

data class Language(
    override val title: String = "Language",
    override val configs: List<String> = listOf("English", "Korean", "Japanese", "Chinese")
) : Settings

data class ConnectGoogleTasks(
    override val title: String = "ConnectGoogleTasks",
    override val configs: List<String> = listOf("On", "Off")
) : Settings

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                title = Res.string.settings_screen_title,
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
            general(
                onClickListItem = {
                    showBottomSheet = true
                }
            )
            moreOptions(
                onClickListItem = {
                    showBottomSheet = true
                }
            )
        }

        if (showBottomSheet) {
            CustomModalBottomSheet(
                onDismissRequest =  {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Text(
                    text = "test"
                )
            }
        }
    }
}

private fun LazyListScope.general(
    onClickListItem: () -> Unit,
) {
    item {
        SectionTitle(Res.string.settings_screen_general)
    }
    item {
        SectionListItem(
            configTitle = Theme().title,
            selectedConfig = "Follow System",
            selectableConfig = Theme().configs,
            onClick = onClickListItem
        )
    }
    item {
        SectionListItem(
            configTitle = Language().title,
            selectedConfig = "Follow System",
            selectableConfig = Language().configs,
            onClick = onClickListItem
        )
    }
    item {
        HorizontalDivider(
            thickness = 1.dp
        )
    }
}

private fun LazyListScope.moreOptions(
    onClickListItem: () -> Unit,
) {
    item {
        SectionTitle(Res.string.settings_screen_more_options)
    }
    item {
        SectionListItem(
            configTitle = ConnectGoogleTasks().title,
            selectedConfig = "Off",
            selectableConfig = ConnectGoogleTasks().configs,
            onClick = onClickListItem
        )
    }
    item {
        HorizontalDivider(
            thickness = 1.dp
        )
    }
}

@Composable
private fun SectionTitle(
    title: StringResource,
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
private fun SectionListItem(
    configTitle: String,
    selectedConfig: String,
    // TODO
    selectableConfig: List<String>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
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