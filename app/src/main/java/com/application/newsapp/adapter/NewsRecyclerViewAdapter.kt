package com.application.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.newsapp.R
import com.application.newsapp.activities.NewsDetail
import com.application.newsapp.model.NewsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*
import java.util.*

class NewsRecyclerViewAdapter(
    private var context: Context,
    private var news: ArrayList<NewsItem>
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        //inflate the layout file
        val newsView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(newsView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val newsItem = news[position]

        Glide.with(context)
            .load(newsItem.image)
            .into(holder.newsImageView)

        holder.newsText.text = newsItem.title
        holder.newsDate.text = newsItem.date.toString()

        holder.itemView.setOnClickListener {

            val i = Intent(context, NewsDetail::class.java)
            i.putExtra("selectedNewsID", newsItem.id)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var newsImageView: ImageView = view.newsImageView
        internal var newsText: TextView = view.newsTitle
        internal var newsDate: TextView = view.newsDate
    }
}