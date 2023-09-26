package com.ardidong.core.genre

import com.ardidong.core.genre.mapper.GenreMapper
import com.ardidong.domain.common.ResultOf
import com.ardidong.domain.genre.GenreRepository
import com.ardidong.domain.genre.model.Genre
import kotlinx.coroutines.delay
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val remote: GenreRemoteDataSource
) : GenreRepository {
    private val mapper = GenreMapper()

    override suspend fun getGenreList(): ResultOf<List<Genre>>{
        return remote.getGenreList().fold(
            success = { response ->
                ResultOf.Success( mapper.toModel(response))
            },
            failure = {
                ResultOf.Failure(it)
            }
        )
    }
}