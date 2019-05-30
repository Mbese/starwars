package com.example.starswars.view

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.starswars.R
import com.example.starswars.model.FilmListModel
import com.example.starswars.viewmodel.FilmsViewModel

class MainActivity : AppCompatActivity() {
    private val filmList = arrayListOf(1,2,3,4,5,6,7)
    private val filmInfoList = ArrayList<FilmListModel>()
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressDialog? = null

    private val viewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(FilmsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = ProgressDialog(this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView!!.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        progressBar!!.setTitle("Please wait")
        progressBar!!.setMessage("Loading Star Wars Films.........")

        progressBar!!.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFD4D9D0")))

        progressBar!!.isIndeterminate = false
        progressBar!!.setCancelable(false)
    }

    override fun onResume() {
        super.onResume()

        progressBar!!.show()

        if (!filmList.isEmpty()){
            for (film in filmList) {
                viewModel.getFilm(film)
                observeResponseData()
            }
        }

        progressBar!!.hide()
    }

    private fun showFilmFullInfo(model: FilmListModel) {
        val intent = Intent(this, FilmDetailsActivity::class.java)
        intent.putExtra("episode_id", model.episode_id)
        intent.putExtra("title", model.title)
        intent.putExtra("year", model.created)
        intent.putExtra("director", model.director)
        intent.putExtra("release_date", model.release_date)

        intent.putStringArrayListExtra("characters", model.characters)
        startActivity(intent)
    }

    private fun observeResponseData() {
        viewModel.liveData.observe(this, Observer { data ->
            if (data != null) {
                filmInfoList.add(data)

                val adapter = FilmAdapter(filmInfoList,  object : FilmAdapter.ItemClickListener {
                    override fun onItemClicked(model: FilmListModel) {
                        showFilmFullInfo(model)
                    }
                })
                recyclerView!!.adapter = adapter

            }
        })
    }
}
