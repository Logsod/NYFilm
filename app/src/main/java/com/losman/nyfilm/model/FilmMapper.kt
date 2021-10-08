package com.losman.nyfilm.model

import com.losman.nyfilm.retrofit.NYListResponse

//мапер из ответа сервера в модель
class FilmMapper {
    private fun fromResponseFilmItem(response: NYListResponse.Result): Film {
        return Film(
            title = response.headline,
            image = response.multimedia?.src ?: "",
            description = response.summaryShort
        )
    }

    fun fromResponse(response: NYListResponse) : List<Film>
    {
        return response.results.map {
            fromResponseFilmItem(it)
        }
    }
}