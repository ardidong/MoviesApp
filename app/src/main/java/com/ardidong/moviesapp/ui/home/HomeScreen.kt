package com.ardidong.moviesapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ardidong.moviesapp.R
import com.ardidong.moviesapp.ui.UiState
import com.ardidong.moviesapp.ui.component.ErrorCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeScreenState = homeViewModel.homesState.collectAsState()

    HomeScreenContent(
        modifier = modifier,
        homeScreenState = homeScreenState.value,
        onMovieClicked = { id ->
            navController.navigate("detail/$id"){
                launchSingleTop = true
            }
        },
        onDataRetry = {
            homeScreenState.value.genreState.let {
                if (it is UiState.Error){
                    homeViewModel.getGenreList()
                }
            }
        },
        onAddGenre = { homeViewModel.addGenre(it) },
        onRemoveGenre = { homeViewModel.removeGenre(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier,
    homeScreenState: HomeScreenState,
    onMovieClicked: (Int) -> Unit,
    onDataRetry: () -> Unit,
    onAddGenre: (Int) -> Unit,
    onRemoveGenre: (Int) -> Unit,
){
    val movieData = homeScreenState.movies.collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            GenreTab(
                genreState = homeScreenState.genreState,
                selectedGenre = homeScreenState.selectedGenre,
                onAddGenre = { onAddGenre(it) },
                onRemoveGenre = { onRemoveGenre(it) }
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ){
                items(movieData.itemCount){ index ->
                    movieData[index]?.let { movie ->
                        MovieItem(movie = movie){ id -> onMovieClicked(id) }
                    }
                }

                movieData.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { MovieLoading() }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { MovieLoading() }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                ErrorCard(
                                    title = stringResource(id = R.string.error_title_general),
                                    message = stringResource(R.string.error_get_data_movies, )
                                ) {
                                    retry()
                                }
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            item {
                                ErrorCard(
                                    title = stringResource(id = R.string.error_title_general),
                                    message = stringResource(R.string.error_get_data_movies, )
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
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun HomePreview() {
//
//    Scaffold {
//        HomeScreenContent(modifier = Modifier.padding(it), UiState.Loading, UiState.Loading,{},{})
//
//    }
//}