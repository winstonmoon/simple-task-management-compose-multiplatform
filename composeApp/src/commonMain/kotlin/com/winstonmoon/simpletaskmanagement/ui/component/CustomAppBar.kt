package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: StringResource,
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    onClickBack: (() -> Unit)? = null,
) {
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier,
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface),
            navigationIcon = {
                if (drawerState != null) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                        )
                    }
                } else {
                    if (onClickBack != null) {
                        IconButton(
                            onClick = onClickBack
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                }
            },
            title = {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )
        HorizontalDivider(
            modifier = Modifier
                .align(Alignment.BottomStart),
            thickness = 1.dp,
            color = Color(0xFF30363D)
        )
    }
}