package com.ardidong.moviesapp.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ardidong.moviesapp.ui.theme.MoviesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectButton(
    text: String = "Genre",
    isSelected: Boolean,
    onClick: (isSelected: Boolean) -> Unit )
{
    val isSelected = remember{ mutableStateOf(isSelected) }

    FilterChip(
        selected = isSelected.value,
        label = { Text(text) },
        onClick = {
            isSelected.value = !isSelected.value
            onClick(isSelected.value)
        },
        border = FilterChipDefaults.filterChipBorder(
            borderColor = MaterialTheme.colorScheme.secondary,
            selectedBorderColor = MaterialTheme.colorScheme.secondary,
            borderWidth = 1.dp,
            selectedBorderWidth = 1.dp
        ),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.secondary,
            selectedLabelColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun SelectButtonPrev() {
    MoviesAppTheme {
//        SelectButton(){}
    }
}