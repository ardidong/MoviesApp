package com.ardidong.moviesapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.moviesapp.ui.component.ExpandableText
import com.ardidong.moviesapp.ui.shimmerEffect


@Composable
fun ReviewItem(
    modifier: Modifier = Modifier,
    review: MovieReview
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.elevatedCardColors(),)
    {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ){
                review.authorDetails.avatarPath.apply {
                    if (this.isNotBlank()){
                        AsyncImage(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            model = "https://image.tmdb.org/t/p/w185${this}",
                            contentDescription = ""
                        )
                    } else {

                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = review.author, style = MaterialTheme.typography.titleMedium)
            }

            ExpandableText(text = review.content)
        }
    }
}

@Composable
fun ReviewLoading(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.elevatedCardColors(),)
    {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ){
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(16.dp))

                Box ( modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .shimmerEffect(),
                )
            }

            Box ( modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .shimmerEffect(),
            )
        }
    }
}