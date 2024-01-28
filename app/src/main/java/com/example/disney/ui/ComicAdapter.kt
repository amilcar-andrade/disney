package com.example.disney.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.disney.R
import com.squareup.picasso.Picasso

internal class ComicAdapter(
    private val picasso: Picasso,
    private val listener: ClickListener
) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    private val items: MutableList<ComicListViewItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val holder = ComicViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_comic, parent, false))
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(items[position])
            }
        }
        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(picasso, items[position])
    }

    internal fun updateItems(items: List<ComicListViewItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    internal class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTv = ViewCompat.requireViewById<TextView>(itemView, R.id.name)
        private val summaryTv = ViewCompat.requireViewById<TextView>(itemView, R.id.summary)
        private val photoImg = ViewCompat.requireViewById<ImageView>(itemView, R.id.image)

        fun bind(picasso: Picasso, item: ComicListViewItem) {
            if (item.imageUrl.isNotBlank()) {
                picasso.load(item.imageUrl).into(photoImg)
            }
            nameTv.text = item.name
            summaryTv.text = item.summary
        }
    }

    internal interface ClickListener {

        fun onClick(item: ComicListViewItem)
    }
}