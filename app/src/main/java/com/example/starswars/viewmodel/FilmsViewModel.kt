package com.example.starswars.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.starswars.model.FilmListModel
import com.example.starswars.repository.FilmListRepository

class FilmsViewModel: ViewModel() {
    val liveData = MutableLiveData <FilmListModel>()
    val repo = FilmListRepository()

    fun getFilm(filmNumber: Int?){
        repo.getFilmDetails(liveData, filmNumber)
    }
}