package com.application.newsapp.network

import com.application.newsapp.model.CommentsResponse
import com.application.newsapp.model.NewsCategoriesResponse
import com.application.newsapp.model.NewsResponse
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.*

interface NetworkAPI {

    @GET("getall")
    fun getNews(): Observable<NewsResponse>

    @GET("getbycategoryid/{id}")
    fun getNewsByCategory(@Path("id") id : Int): Observable<NewsResponse>

    @GET("getnewsbyid/{id}")
    fun getNewsByID(@Path("id") id : Int): Observable<NewsResponse>

    @GET("getcommentsbynewsid/{id}")
    fun getCommentsByID(@Path("id") id : Int): Observable<CommentsResponse>

    @GET("getallnewscategories")
    fun getCategories(): Observable<NewsCategoriesResponse>

    @POST("savecomment")
    fun postComment(@Body json : JSONObject): Observable<CommentsResponse>

}