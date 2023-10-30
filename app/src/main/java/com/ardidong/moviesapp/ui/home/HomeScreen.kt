package com.ardidong.moviesapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ardidong.moviesapp.R
import com.ardidong.moviesapp.ui.UiState
import com.ardidong.moviesapp.ui.component.ErrorCard
import com.ardidong.moviesapp.ui.component.MovieAppBar
import com.ardidong.moviesapp.ui.component.SearchBar
import kotlinx.coroutines.launch

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
        onRetryGenre = { homeViewModel.getGenreList() },
        onRemoveGenre = { homeViewModel.removeGenre(it) },
        onSearch = { homeViewModel.getMovie(it) }
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
    onRetryGenre: () -> Unit,
    onRemoveGenre: (Int) -> Unit,
    onSearch: (String) -> Unit,
){
    val movieData = homeScreenState.movies.collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier,
        topBar = {
            MovieAppBar(title = stringResource(id = R.string.app_name))
        }
    ) { paddingValues ->
        Box {
            val listState = rememberLazyListState()
            val focusManager = LocalFocusManager.current

            val showScrollTopTopButton by remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex > 0
                }
            }

            Column(modifier = Modifier.padding(paddingValues)) {
                SearchBar(
                    onSearch = { onSearch(it) }
                )

                GenreTab(
                    genreState = homeScreenState.genreState,
                    selectedGenre = homeScreenState.selectedGenre,
                    onAddGenre = { onAddGenre(it) },
                    onRetryGenre = { onRetryGenre() },
                    onRemoveGenre = { onRemoveGenre(it) }
                )

                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    items(movieData.itemCount) { index ->
                        movieData[index]?.let { movie ->
                            MovieItem(movie = movie) { id ->
                                focusManager.clearFocus()
                                onMovieClicked(id)
                            }
                        }
                    }

                    movieData.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                items(
                                    count = 3
                                ) {
                                    MovieLoading()
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item { MovieLoading() }
                            }

                            loadState.append is LoadState.Error -> {
                                item {
                                    ErrorCard(
                                        title = stringResource(id = R.string.error_title_general),
                                        message = stringResource(R.string.error_get_data_movies,)
                                    ) {
                                        retry()
                                    }
                                }
                            }

                            loadState.refresh is LoadState.Error -> {
                                item {
                                    ErrorCard(
                                        title = stringResource(id = R.string.error_title_general),
                                        message = stringResource(R.string.error_get_data_movies,)
                                    ) {
                                        onDataRetry()
                                        retry()
                                    }
                                }
                            }
                        }
                    }
                }
            }

            val scope = rememberCoroutineScope()
            ScrollUpButton(isVisible = showScrollTopTopButton) {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }
    }
}

@Composable
fun ScrollUpButton(
    isVisible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = isVisible, enter = slideIn(
        initialOffset = {
            IntOffset(
                0,
                350
            )
        }
    ), exit = slideOut(
        animationSpec = tween(700),
        targetOffset = {
            IntOffset(
                0, 350
            )
        }
    )) {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .height(48.dp),
                containerColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    onClick()
                }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon( modifier = Modifier.padding(vertical = 2.dp),tint = MaterialTheme.colorScheme.onSecondary ,imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "up arrow")
                    Text( modifier = Modifier.padding(vertical = 2.dp), text = "Scroll to top", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}