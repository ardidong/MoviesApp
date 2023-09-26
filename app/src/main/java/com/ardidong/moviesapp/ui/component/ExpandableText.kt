package com.ardidong.moviesapp.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    maxLines: Int = 5
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textStyle = MaterialTheme.typography.bodyMedium

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(100))
    ) {
        if (isExpanded) {
            Text(text = text, style = textStyle)
        } else {
            Text(text = text, style = textStyle , maxLines = maxLines, overflow = TextOverflow.Ellipsis)
        }

        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isExpanded = !isExpanded
                },
            text = if (isExpanded) " Read Less" else " Read More",
            textAlign = TextAlign.Center,
            color = Color.Blue,
        )
    }

}