package com.ardidong.moviesapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ardidong.domain.movie.model.Movie
import com.ardidong.moviesapp.ui.shimmerEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    movie: Movie,
    onMovieClicked: (Int) -> Unit
) {
    val aspectRatio = 500f / 750f

    Card( modifier = Modifier
        .fillMaxWidth()
        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
        .clip(shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.elevatedCardColors(),
        onClick = {
            onMovieClicked(movie.id)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(75.dp)
                    .aspectRatio(aspectRatio)
                    .clip(shape = RoundedCornerShape(16.dp))
                ,
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "",
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Text(
                    modifier = Modifier.padding(top= 8.dp),
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 16.sp
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}


@Composable
fun MovieLoading() {
    Card( modifier = Modifier
        .fillMaxWidth()
        .shadow(8.dp, shape = RoundedCornerShape(16.dp))
        .clip(shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.elevatedCardColors(),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val aspectRatio = 500f / 750f

            Box ( modifier = Modifier
                .width(75.dp)
                .aspectRatio(aspectRatio)
                .clip(shape = RoundedCornerShape(16.dp))
                .shimmerEffect(),
            ){
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

                Box ( modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .shimmerEffect(),
                )

                Box ( modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .shimmerEffect(),
                )
            }
        }

    }
}