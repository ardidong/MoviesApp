package com.ardidong.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ardidong.domain.common.ErrorEntity
import com.ardidong.domain.movie.GetMovieDetailUseCase
import com.ardidong.domain.movie.GetMovieReviewUseCase
import com.ardidong.domain.movie.GetMovieVideoUseCase
import com.ardidong.domain.movie.model.MovieReview
import com.ardidong.moviesapp.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dispatchGetDetail : GetMovieDetailUseCase,
    private val dispatchGetReview : GetMovieReviewUseCase,
    private val dispatchGetVideo : GetMovieVideoUseCase
): ViewModel() {

    private var _detailState = MutableStateFlow(DetailScreenState.getInitial())
    val detailState: StateFlow<DetailScreenState> get() = _detailState

    private val _reviewState: MutableStateFlow<PagingData<MovieReview>> = MutableStateFlow(value = PagingData.empty())
    val reviewState: MutableStateFlow<PagingData<MovieReview>> get() = _reviewState

    fun getMovieDetail(movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            updateState { it.copy( detail = UiState.Loading ) }
            dispatchGetDetail(movieId).fold(
                success = { detail ->
                    updateState { it.copy( detail = UiState.Success(detail) ) }
                },
                failure = { error ->
                    updateState { it.copy( detail = UiState.Error(error) ) }
                }
            )
        }
    }

    fun getReview(movieId: Int){
        _reviewState.value = PagingData.empty()
        viewModelScope.launch(Dispatchers.IO){
            dispatchGetReview(movieId)
                .cachedIn(viewModelScope)
                .collect {
                    _reviewState.value = it
                }
        }
    }

    fun getVideo(movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            updateState { it.copy( video = UiState.Loading ) }

            dispatchGetVideo(movieId).fold(
                success = { list ->
                    if (list.isEmpty()){
                        updateState { it.copy(video = UiState.Error(ErrorEntity.NotFoundError("Video not found"))) }
                        return@launch
                    }

                    val video = list.find { it.site.equals("youtube", true)  && it.type.equals("teaser", true)}
                    if (video != null){
                        updateState { it.copy( video =  UiState.Success(video) ) }
                        return@launch
                    }

                    updateState { it.copy(video = UiState.Error(ErrorEntity.NotFoundError("Video not found"))) }
                },
                failure = {}
            )
        }
    }

    private fun updateState(update: (DetailScreenState) -> DetailScreenState) {
        _detailState.value = update(_detailState.value)
    }
}