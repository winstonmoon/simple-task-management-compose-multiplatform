package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    drawerState: DrawerState?,
    title: String,
) {
    val coroutineScope = rememberCoroutineScope()

    CenterAlignedTopAppBar(
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
            }
        },
        title = {
            Text(text = title)
        }
    )
}