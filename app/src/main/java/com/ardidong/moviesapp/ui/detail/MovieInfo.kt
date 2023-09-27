package com.ardidong.moviesapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ardidong.domain.common.extension.toDurationString
import com.ardidong.domain.movie.model.MovieDetail
import com.ardidong.moviesapp.BuildConfig
import com.ardidong.moviesapp.ui.shimmerEffect

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    detail: MovieDetail,
    onReviewClick: () -> Unit
) {
    val aspectRatio = 500f / 750f

    Column(modifier = modifier) {
        Row{
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
                    .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp))
                    .clip(shape = RoundedCornerShape(32.dp))
                ,
                model = "${BuildConfig.IMAGE_BASE_URL}w500${detail.posterPath}",
                contentDescription = "${detail.title} poster",
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Duration",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = detail.runtime.toDurationString(),
                    textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium
                )

                val genres = detail.genres.joinToString("\n") { it.name }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Genre",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(
                    text = genres,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium)

                val rating = String.format("%.1f",
                    detail.voteAverage)
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Rating",  style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                Text(text = "$rating/10", style = MaterialTheme.typography.bodyMedium)
                TextButton(onClick = { onReviewClick() }) {
                    Text(text = "See Reviews")
                }

            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = detail.title, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = detail.overview,
            style = MaterialTheme.typography.bodyMedium,
        )
    }


}

@Composable
fun MovieInfoLoading(
    modifier: Modifier = Modifier
) {
    val aspectRatio = 500f / 750f
    Column(modifier = modifier) {
        Row{
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(aspectRatio)
                    .clip(shape = RoundedCornerShape(32.dp))
                    .shimmerEffect(),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp)
                        .height(20.dp)
                        .shimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp)
                        .height(20.dp)
                        .shimmerEffect()
                )

                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(0.5f)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(top = 2.dp)
                        .height(20.dp)
                        .shimmerEffect()
                )
            }
        }


        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.5f)
                .height(26.dp)
                .shimmerEffect()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(48.dp)
                .shimmerEffect()
        )
    }
}