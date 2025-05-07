package com.natasha.pockemon.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import pockemon.composeapp.generated.resources.Res
import pockemon.composeapp.generated.resources.try_again

@Composable
fun ErrorView(errorMessage: String, modifier: Modifier = Modifier, onRetryPress: (() -> Unit)) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            modifier = Modifier
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )

        Button(
            onClick = { onRetryPress.invoke() },
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(Res.string.try_again)
            )
        }
    }
}