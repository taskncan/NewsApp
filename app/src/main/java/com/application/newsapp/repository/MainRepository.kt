package com.application.newsapp.repository

import android.content.Context
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.application.newsapp.viewmodel.MainViewModel
import com.application.newsapp.R
import com.application.newsapp.network.NetworkAPI
import com.application.newsapp.network.NetworkClient
import com.application.newsapp.network.NetworkCompanions.Companion.baseUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class MainRepository(
    private var context: Context,
    private var viewModel: MainViewModel
) {

    private var mainViewModel: MainViewModel = viewModel
    private var progressDialog: SweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)

    init {

        progressDialog.progressHelper.barColor = ContextCompat.getColor(context,
            R.color.colorAccent
        )
        progressDialog.titleText = "Loading"
        progressDialog.progressHelper.circleRadius = 100
        progressDialog.setCancelable(false)

    }

    fun getNews() {

        progressDialog.show()

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getNews()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    getCategories()
                    mainViewModel.newsResponse.value = response


                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

    fun getNewsByCategory(id: Int) {

        progressDialog.show()

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getNewsByCategory(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    getCategories()
                    mainViewModel.newsResponse.value = response


                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

    fun getComments(id: Int) {

        progressDialog.show()

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getCommentsByID(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    getCategories()
                    mainViewModel.commentsResponse.value = response


                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

    fun postComment(name: String,comment : String,newsID : Int) {

        progressDialog.show()

        val json = "{\"name\":\"$name\",\"text\":\"$comment\",\"news_id\":\"$newsID\"}"

        val jsonObject = JSONObject(json)

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .postComment(jsonObject)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Successful")
                        .setContentText("Comment send successfully")
                        .show()

                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

    fun getCategories() {

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getCategories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    progressDialog.dismiss()
                    mainViewModel.newsCategoriesResponse.value = response


                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

    fun getNewsByID(id : Int) {

        progressDialog.show()

        val request = NetworkClient.getClient(baseUrl)
            .create(NetworkAPI::class.java)
            .getNewsByID(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->

                    progressDialog.dismiss()
                    mainViewModel.newsDetailResponse.value = response


                },
                {

                    progressDialog.dismiss()
                    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!\n" + it.message)
                        .show()
                }
            )
    }

}