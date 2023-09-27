package com.ardidong.moviesapp.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.moviesapp.R
import com.ardidong.moviesapp.ui.component.ErrorCard
import com.ardidong.moviesapp.ui.component.MovieAppBar

@Composable
fun ReviewScreen(
    navController: NavController,
    movieId: Int,
    detailViewModel: DetailViewModel = hiltViewModel())
{
    LaunchedEffect(movieId){
        detailViewModel.getReview(movieId)
    }

    val reviewState = detailViewModel.reviewState.collectAsLazyPagingItems()
    ReviewScreenContent(reviewState = reviewState){
        navController.navigateUp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreenContent(
    reviewState: LazyPagingItems<MovieReview>,
    onNavBack: () -> Unit
) {
    Scaffold(
        topBar = {
            MovieAppBar(
                modifier = Modifier.shadow(16.dp),
                title = stringResource(id = R.string.title_review)
            ) {
                onNavBack()
            }
        }
    ){

        if (
            reviewState.itemSnapshotList.isEmpty() &&
            reviewState.loadState.refresh is LoadState.NotLoading
        ){
            Box (
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = stringResource(id = R.string.empty_review),
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            }

            return@Scaffold
        }


        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ){
            items(reviewState.itemCount){ index ->
                reviewState[index]?.let { review ->
                    ReviewItem(review = review)
                }
            }

            reviewState.apply {
                when{
                    loadState.refresh is LoadState.Loading -> {
                        item { ReviewLoading() }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { ReviewLoading() }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            ErrorCard(
                                title = stringResource(id = R.string.error_title_general),
                                message = stringResource(id = R.string.error_get_data_review)
                            ) {
                                retry()
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            ErrorCard(
                                title = stringResource(id = R.string.error_title_general),
                                message = stringResource(id = R.string.error_get_data_review)
                            ) {
                                retry()
                            }
                        }
                    }
                }
            }
        }
    }
}


