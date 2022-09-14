package com.application.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigInteger

class NewsItem {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("text")
    @Expose
    var text: String? = null
    @SerializedName("date")
    @Expose
    var date: BigInteger? = null
    @SerializedName("image")
    @Expose
    var image: String? = null
    @SerializedName("categoryName")
    @Expose
    var categoryName: String? = null

}