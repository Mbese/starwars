package com.example.starswars.view

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.starswars.R
import com.example.starswars.model.FilmListModel

class FilmAdapter (
    filmList: ArrayList<FilmListModel>,
    private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<FilmAdapter.MyViewHolder>() {
    val filmSet = HashSet<FilmListModel>(filmList)

    val nonDuplicate = ArrayList<FilmListModel>(filmSet)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
        val rootView = getLayoutInflater(viewGroup.context).inflate(R.layout.film_row, viewGroup, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
       return nonDuplicate.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val filmData = nonDuplicate[position]
        val titleTextView = viewHolder.titleTextView
        val releaseDateTextView = viewHolder.releaseDateTextView

        titleTextView.text = nonDuplicate[position].title
        releaseDateTextView.text = nonDuplicate[position].release_date

        viewHolder.itemView.setOnClickListener { itemClickListener.onItemClicked(filmData) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(model: FilmListModel)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var titleTextView: TextView = itemView.findViewById(R.id.film_title)
        internal var releaseDateTextView: TextView = itemView.findViewById(R.id.release_date)
    }
}