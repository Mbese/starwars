package com.example.starswars.repository

import android.arch.lifecycle.MutableLiveData
import com.example.starswars.model.FilmListModel
import com.example.starswars.model.CharacterModel

interface IMoviesListRepository {
    fun getFilmCharacters(liveData: MutableLiveData<CharacterModel>, character: Int?)
    fun getFilmDetails(liveData: MutableLiveData<FilmListModel>, filmNumber: Int?)
}