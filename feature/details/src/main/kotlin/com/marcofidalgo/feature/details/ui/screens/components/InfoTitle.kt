package com.marcofidalgo.feature.details.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InfoLabel(label: String) {
    Text(
        modifier = Modifier
            .wrapContentSize(),
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Justify,
        text = label
    )
}

@Composable
fun InfoText(text: String?) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        text = text ?: ""
    )
}

@Composable
fun InfoContainer(label: String, text: String?) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        InfoLabel(label)
        InfoText(text)
    }
}
