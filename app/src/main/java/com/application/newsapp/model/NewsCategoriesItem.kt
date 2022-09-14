package com.application.newsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsCategoriesItem {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}