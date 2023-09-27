package com.ardidong.moviesapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ardidong.domain.genre.model.Genre
import com.ardidong.moviesapp.ui.UiState
import com.ardidong.moviesapp.ui.shimmerEffect

@Composable
fun GenreTab(
    modifier: Modifier = Modifier,
    genreState: UiState<List<Genre>>,
    selectedGenre: Set<Int>,
    onAddGenre: (Int) -> Unit,
    onRetryGenre: () -> Unit,
    onRemoveGenre: (Int) -> Unit,
) {

    if (genreState is UiState.Error){
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), contentAlignment = Alignment.Center){
            Button(
                modifier = Modifier.height(36.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                onClick = { onRetryGenre() }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "retry icon")
                    Text(modifier = Modifier.padding(start = 8.dp) ,text = "Retry get genre")
                }
            }
        }
        return
    }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        when(genreState){
            is UiState.Success -> {
                items(genreState.data){ genre ->
                    val isGenreSelected = selectedGenre.contains(genre.id)
                    SelectButton(text = genre.name, isSelected = isGenreSelected){ isSelected ->
                        if (isSelected){
                            onAddGenre(genre.id)
                        }else{
                            onRemoveGenre(genre.id)
                        }
                    }
                }
            }

            is UiState.Loading -> {
                items(5){
                    Box(
                        modifier = Modifier.width(75.dp).padding(vertical = 8.dp).shimmerEffect()
                    ){
                        Text(text = "")
                    }
                }
            }

            else -> {}
        }
    }
}