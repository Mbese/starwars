package com.example.starswars.view

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.TextView
import com.example.starswars.R
import com.example.starswars.viewmodel.FilmCharacterViewModel

class FilmDetailsActivity : AppCompatActivity() {

    private val viewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(FilmCharacterViewModel::class.java)
    }

    private var recyclerView: RecyclerView? = null
    private val characterList = ArrayList<String>()
    private var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressBar = ProgressDialog(this)
        recyclerView = findViewById<RecyclerView>(R.id.characters_recycler_view)
        recyclerView!!.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        progressBar!!.setTitle("Please wait")
        progressBar!!.setMessage("Loading Film Characters.........")

        progressBar!!.window!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFD4D9D0")))

        progressBar!!.isIndeterminate = false
        progressBar!!.setCancelable(false)

        val episodeTextView = findViewById<TextView>(R.id.episode_number_text)
        val titleTextView = findViewById<TextView>(R.id.film_title_text)
        val yearTextView = findViewById<TextView>(R.id.film_year_text)
        val directorTextView = findViewById<TextView>(R.id.film_director_text)
        val releaseDateTextView = findViewById<TextView>(R.id.release_date_text)

        val intent = intent

        if(intent != null && intent.extras != null){
            episodeTextView.text = intent.getStringExtra("episode_id")
            titleTextView.text = intent.getStringExtra("title")
            yearTextView.text = intent.getStringExtra("year")
            directorTextView.text = intent.getStringExtra("director")
            releaseDateTextView.text = intent.getStringExtra("release_date")

            val listOfCharacter = intent.getStringArrayListExtra("characters")
            for (character in listOfCharacter) {
                val index = character.length - 2
                val value = character.substring(index, index + 1).toIntOrNull()
                progressBar!!.show()

                viewModel.getCharacter(value)
                observeResponseData()

                progressBar!!.hide()
            }

        }

    }

    private fun observeResponseData() {
        viewModel.liveData.observe(this, Observer { data ->
            if (data != null) {
                characterList.add(data.name)
                val adapter = CharacterAdapter(characterList)
                recyclerView!!.adapter = adapter
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
