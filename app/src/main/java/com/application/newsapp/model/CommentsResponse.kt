package com.application.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentsResponse {
    @SerializedName("serviceMessageCode")
    @Expose
    var serviceMessageCode: Int? = null
    @SerializedName("serviceMessageText")
    @Expose
    var serviceMessageText: String? = null
    @SerializedName("items")
    @Expose
    var items: List<CommentsItem>? = null

}