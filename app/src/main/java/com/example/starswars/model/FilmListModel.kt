package com.example.starswars.model

data class FilmListModel(val title: String, val episode_id: String, val opening_crawl: String,
                         val director: String, val producer: String, val release_date: String,
                         val characters: ArrayList<String>, val planets: ArrayList<String>, val starships: ArrayList<String>,
                         val vehicles: ArrayList<String>, val species: ArrayList<String>, val created: String,
                         val edited: String, val url: String)