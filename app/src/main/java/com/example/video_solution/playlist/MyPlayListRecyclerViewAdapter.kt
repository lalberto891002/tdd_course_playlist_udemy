package com.example.video_solution.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.video_solution.R

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPlayListRecyclerViewAdapter(
    private val values: List<PlayList>
) : RecyclerView.Adapter<MyPlayListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.playlist_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playListName.text = item.name
        holder.categoryView.text = item.category
        holder.imageView.setImageResource(R.drawable.playlist)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playListName: TextView = view.findViewById<TextView>(R.id.palylist_name)
        val categoryView: TextView = view.findViewById<TextView>(R.id.list_category)
        val imageView:ImageView = view.findViewById<ImageView>(R.id.playlist_image)

        override fun toString(): String {
            return super.toString() + " '" + playListName.text + "'"
        }
    }

}