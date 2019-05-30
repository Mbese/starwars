package com.example.starswars.view

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.starswars.R

class CharacterAdapter(private val characters: ArrayList<String>):  RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    val characterSet = HashSet<String>(characters)
    val nonDuplicateList = ArrayList<String>(characterSet)

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CharacterAdapter.MyViewHolder {
        val rootView = getLayoutInflater(viewGroup.context).inflate(R.layout.characters_row, viewGroup, false)
        return CharacterAdapter.MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return nonDuplicateList.size
    }

    override fun onBindViewHolder(viewHolder: CharacterAdapter.MyViewHolder, position: Int) {
        val characterTextView = viewHolder.characterTextView

        characterTextView.text = nonDuplicateList[position]
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var characterTextView: TextView = itemView.findViewById(R.id.character_text)
    }
}