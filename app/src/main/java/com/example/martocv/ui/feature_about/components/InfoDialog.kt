package com.example.martocv.ui.feature_about.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.martocv.R


@Composable
fun InfoDialog(
    title: String,
    @StringRes descriptionResId: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        text = {
            Text(
                text = stringResource(id = descriptionResId),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.dialog_close))
            }
        }
    )
}