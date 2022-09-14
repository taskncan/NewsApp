package com.application.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.newsapp.R
import com.application.newsapp.model.CommentsItem
import kotlinx.android.synthetic.main.item_comments.view.*
import java.util.*

class CommentsRecyclerViewAdapter(
    private var context: Context,
    private var comments: ArrayList<CommentsItem>
) : RecyclerView.Adapter<CommentsRecyclerViewAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        //inflate the layout file
        val newsView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comments, parent, false)

        return CommentsViewHolder(newsView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {

        val commentItem = comments[position]

        holder.name.text = commentItem.name
        holder.comment.text = commentItem.text

    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var comment: TextView = view.comment
        internal var name: TextView = view.name
    }
}