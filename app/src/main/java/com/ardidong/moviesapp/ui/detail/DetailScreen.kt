package com.ardidong.moviesapp.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ardidong.moviesapp.R
import com.ardidong.moviesapp.ui.UiState
import com.ardidong.moviesapp.ui.component.MovieAppBar
import com.ardidong.moviesapp.ui.component.YoutubeVideoPlayer

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieId: Int,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(movieId){
        detailViewModel.getMovieDetail(movieId)
        detailViewModel.getVideo(movieId)
    }

    val detailScreenState by detailViewModel.detailState.collectAsState()

    DetailScreenContent(
        modifier = modifier,
        detailState = detailScreenState,
        onReviewClick = {
            navController.navigate("review/${movieId}")
        },
        onNavBack = {
            navController.navigateUp()
        },
        onRetryClick = {
            detailViewModel.getMovieDetail(movieId)
            detailViewModel.getVideo(movieId)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    detailState: DetailScreenState,
    onReviewClick : () -> Unit,
    onNavBack: () -> Unit,
    onRetryClick: () -> Unit
) {

    Scaffold(
        topBar = {
            MovieAppBar(
                modifier = Modifier.shadow(16.dp),
                title = stringResource(id = R.string.title_details),
                onBackNavigation = { onNavBack() }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when(detailState.detail){
                is UiState.Success -> {
                    val detailData = detailState.detail.data
                    MovieInfo(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        detail = detailData,
                        onReviewClick = {
                            onReviewClick()
                        }
                    )
                }
                is UiState.Loading -> {
                    MovieInfoLoading(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                    )
                }

                else -> {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.error_title_general), style = MaterialTheme.typography.titleMedium)
                        Text(text = stringResource(id = R.string.error_get_data_detail), textAlign = TextAlign.Center ,style = MaterialTheme.typography.bodyMedium)

                        Button(
                            modifier = Modifier.padding(top = 8.dp),
                            onClick = { onRetryClick() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text(text = stringResource(id = R.string.label_retry))
                        }
                    }

                }
            }

            if (detailState.video is UiState.Success && detailState.detail is UiState.Success){
                Text(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Trailer", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )

                YoutubeVideoPlayer(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.8f)
                    ,
                    id = detailState.video.data.key
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun PreviewDetailScreen() {
    MaterialTheme {
        Scaffold {
            DetailScreenContent(modifier = Modifier.padding(it), detailState = DetailScreenState.getInitial(), {}, {}){}
        }
    }
}