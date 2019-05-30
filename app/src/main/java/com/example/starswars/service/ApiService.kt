package com.example.starswars.service

import com.example.starswars.model.FilmListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("films/{filmNumber}/")
    fun getFilm(@Path("filmNumber") filmNumber: Int?): Call<FilmListModel>
}