package com.ardidong.core.fake

import com.ardidong.core.movie.model.DiscoverMovieResponse
import com.ardidong.core.movie.model.MovieDetailResponse
import com.ardidong.core.movie.model.MovieReviewResponse
import com.ardidong.core.movie.model.MovieVideoResponse

object FakeMovieData {

    fun createMovieReviewResponse() = MovieReviewResponse(
        id = 976573,
        page = 1,
        totalPages = 1,
        totalResults = 5,
        results = listOf(
            MovieReviewResponse.ResultsItem(
                authorDetails = MovieReviewResponse.AuthorDetails(
                    avatarPath = null,
                    name = "",
                    rating = null,
                    username = "garethmb"
                ),
                updatedAt = "2023-06-09T17:49:10.273Z",
                author = "garethmb",
                createdAt = "2023-06-09T17:49:10.193Z",
                id = "64836616bf31f2505880d52a",
                content = "Finding your way in the world can be very difficult and even more so when you are a fire element...",
                url = "https://www.themoviedb.org/review/64836616bf31f2505880d52a"
            ),
            // Add more ResultsItem objects as needed
        )
    )

    fun createMovieDetailResponse() = MovieDetailResponse(
        originalLanguage = "en",
        imdbId = "tt15789038",
        video = false,
        title = "Elemental",
        backdropPath = "/4fLZUr1e65hKPPVw0R3PmKFKxj1.jpg",
        revenue = 490797988,
        genres = listOf(
            MovieDetailResponse.GenresItem(name = "Animation", id = 16),
            MovieDetailResponse.GenresItem(name = "Comedy", id = 35),
            MovieDetailResponse.GenresItem(name = "Family", id = 10751),
            MovieDetailResponse.GenresItem(name = "Fantasy", id = 14),
            MovieDetailResponse.GenresItem(name = "Romance", id = 10749)
        ),
        popularity = 715.087,
        id = 976573,
        voteCount = 2802,
        budget = 200000000,
        overview = "In a city where fire, water, land, and air residents live together, a fiery young woman and a go-with-the-flow guy will discover something elemental: how much they have in common.",
        originalTitle = "Elemental",
        runtime = 102,
        posterPath = "/4Y1WNkd88JXmGfhtWR7dmDAo1T2.jpg",
        productionCompanies = listOf(
            MovieDetailResponse.ProductionCompaniesItem(logoPath = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png", name = "Walt Disney Pictures", id = 2, originCountry = "US"),
            MovieDetailResponse.ProductionCompaniesItem(logoPath = "/1TjvGVDMYsj6JBxOAkUHpPEwLf7.png", name = "Pixar", id = 3, originCountry = "US")
        ),
        releaseDate = "2023-06-14",
        voteAverage = 7.749,
        tagline = "Opposites react.",
        adult = false,
        homepage = "https://movies.disney.com/elemental",
        status = "Released"
    )

    fun createDiscoverMovieResponse(isResultEmpty: Boolean = false): DiscoverMovieResponse {
        return DiscoverMovieResponse(
            page = 1,
            totalPages = 1,
            results = if (isResultEmpty) emptyList() else createMovieItems(),
            totalResults = 815292
        )
    }

    fun createMovieItems(): List<DiscoverMovieResponse.ResultsItem> {
        return listOf(
            DiscoverMovieResponse.ResultsItem(
                "Recently fired and desperate for work, a troubled young man named Mike agrees to take a position as a night security guard at an abandoned theme restaurant: Freddy Fazbear's Pizzeria. But he soon discovers that nothing at Freddy's is what it seems.",
                "en",
                "Five Nights at Freddy's",
                false,
                "Five Nights at Freddy's",
                listOf(27, 9648),
                "/A4j8S6moJS2zNtRR8oWF08gRnL5.jpg",
                "/t5zCBSB5xMDKcDqe91qahCOUYVV.jpg",
                "2023-10-25",
                7124.811,
                8.5,
                507089,
                false,
                906
            ),
            DiscoverMovieResponse.ResultsItem(
                "Armed with every weapon they can get their hands on and the skills to use them, The Expendables are the world’s last line of defense and the team that gets called when all other options are off the table. But new team members with new styles and tactics are going to give “new blood” a whole new meaning.",
                "en",
                "Expend4bles",
                false,
                "Expend4bles",
                listOf(28, 12, 53),
                "/iwsMu0ehRPbtaSxqiaUDQB9qMWT.jpg",
                "/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg",
                "2023-09-15",
                1753.562,
                6.5,
                299054,
                false,
                523
            ),
            DiscoverMovieResponse.ResultsItem(
                "Ethan Hunt and his IMF team embark on their most dangerous mission yet: To track down a terrifying new weapon that threatens all of humanity before it falls into the wrong hands. With control of the future and the world's fate at stake and dark forces from Ethan's past closing in, a deadly race around the globe begins. Confronted by a mysterious, all-powerful enemy, Ethan must consider that nothing can matter more than his mission—not even the lives of those he cares about most.",
                "en",
                "Mission: Impossible - Dead Reckoning Part One",
                false,
                "Mission: Impossible - Dead Reckoning Part One",
                listOf(28, 53),
                "/NNxYkU70HPurnNCSiCjYAmacwm.jpg",
                "/h56edmERPTkey89SqyKu4hINVNy.jpg",
                "2023-07-08",
                1612.907,
                7.7,
                575264,
                false,
                1954
            ),
            DiscoverMovieResponse.ResultsItem(
                "When Ethan Hunt, the leader of a crack espionage team whose perilous operation has gone awry with no explanation, discovers that a mole has penetrated the CIA, he's surprised to learn that he's the No. 1 suspect. To clear his name, Hunt now must ferret out the real double agent and, in the process, even the score.",
                "en",
                "Mission: Impossible",
                false,
                "Mission: Impossible",
                listOf(12, 28, 53),
                "/l5uxY5m5OInWpcExIpKG6AR3rgL.jpg",
                "/sra8XnL96OyLHENcglmZJg6HA8z.jpg",
                "1996-05-22",
                63.929,
                6.97,
                954,
                false,
                8175
            ),
            DiscoverMovieResponse.ResultsItem(
                "When an IMF mission ends badly, the world is faced with dire consequences. As Ethan Hunt takes it upon himself to fulfill his original briefing, the CIA begin to question his loyalty and his motives. The IMF team find themselves in a race against time, hunted by assassins while trying to prevent a global catastrophe.",
                "en",
                "Mission: Impossible - Fallout",
                false,
                "Mission: Impossible - Fallout",
                listOf(28, 12),
                "/AkJQpZp9WoNdj7pLYSj1L0RcMMN.jpg",
                "/aw4FOsWr2FY373nKSxbpNi3fz4F.jpg",
                "2018-07-13",
                73.806,
                7.417,
                353081,
                false,
                7597
            )
        )
    }


    fun createMovieVideoResponse() = MovieVideoResponse(
        id = 1234,
        results = listOf(
            MovieVideoResponse.ResultsItem(
                site = "YouTube",
                size = 1080,
                iso31661 = "US",
                name = "Official Trailer",
                official = true,
                id = "abcd1234",
                type = "Trailer",
                publishedAt = "2023-10-31T12:00:00Z",
                iso6391 = "en",
                key = "xyz123"
            ),
            MovieVideoResponse.ResultsItem(
                site = "Vimeo",
                size = 720,
                iso31661 = "UK",
                name = "Teaser",
                official = false,
                id = "efgh5678",
                type = "Teaser",
                publishedAt = "2023-10-30T14:30:00Z",
                iso6391 = "en",
                key = "lmn456"
            ),
            MovieVideoResponse.ResultsItem(
                site = "Dailymotion",
                size = 480,
                iso31661 = "FR",
                name = "Clip",
                official = false,
                id = "ijkl91011",
                type = "Clip",
                publishedAt = "2023-10-29T10:45:00Z",
                iso6391 = "fr",
                key = "opq789"
            ),
            MovieVideoResponse.ResultsItem(
                site = "Instagram",
                size = 720,
                iso31661 = "US",
                name = "Behind the Scenes",
                official = true,
                id = "rstu121314",
                type = "Featurette",
                publishedAt = "2023-10-28T15:20:00Z",
                iso6391 = "en",
                key = "vwxyz1011"
            )
        )
    )

}