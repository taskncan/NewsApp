package com.application.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentsItem {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("news_id")
    @Expose
    var newsId: Int? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}