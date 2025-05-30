package com.natasha.pockemon.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.natasha.pockemon.utils.UiState
import org.jetbrains.compose.resources.stringResource
import pockemon.composeapp.generated.resources.Res
import pockemon.composeapp.generated.resources.no_data_available
import pockemon.composeapp.generated.resources.something_went_wrong

@Composable
fun <T> UiStateContent(
    uiState: UiState<T>,
    retryCallback: () -> Unit,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {
        FullScreenLoading()
    },
    failureContent: @Composable (String?) -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 24.dp,
                    vertical = 20.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            ErrorView(
                errorMessage = it ?: stringResource(Res.string.something_went_wrong),
                onRetryPress = retryCallback
            )
        }
    },
    successContent: @Composable (T) -> Unit
) {
    Box(modifier = modifier) {
        when (uiState) {
            is UiState.Loading -> {
                loadingContent()
            }
            is UiState.Error -> {
                failureContent(uiState.message)
            }
            is UiState.Success -> {
                if (uiState.data == null) {
                    failureContent(stringResource(Res.string.no_data_available))
                } else {
                    successContent(uiState.data)
                }
            }
        }
    }
}
