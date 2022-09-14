package com.application.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsCategoriesResponse {
    @SerializedName("serviceMessageCode")
    @Expose
    var serviceMessageCode: Int? = null
    @SerializedName("serviceMessageText")
    @Expose
    var serviceMessageText: String? = null
    @SerializedName("items")
    @Expose
    var items: List<NewsCategoriesItem>? = null

}