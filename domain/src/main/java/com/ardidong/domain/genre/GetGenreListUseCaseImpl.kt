package com.ardidong.domain.genre

import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.genre.model.Genre
import javax.inject.Inject

class GetGenreListUseCaseImpl @Inject constructor(
    private val repository: GenreRepository
) : GetGenreListUseCase {
    override suspend fun invoke(): ResultOf<List<Genre>> {
        return repository.getGenreList()
    }
}