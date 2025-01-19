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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.theme.ChineseBlack
import com.winstonmoon.simpletaskmanagement.ui.theme.RomanSilver
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
            title = {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleLarge,
                    color = RomanSilver,
                )
            },
            navigationIcon = {
                drawerState?.let {
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
                            tint = RomanSilver,
                        )
                    }
                }
                onClickBack?.let {
                    IconButton(
                        onClick = onClickBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = RomanSilver,
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(containerColor = ChineseBlack),
        )
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomStart),
            thickness = 1.dp,
            color = RomanSilver,
        )
    }
}