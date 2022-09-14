package com.application.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.newsapp.R
import com.application.newsapp.adapter.NewsRecyclerViewAdapter
import com.application.newsapp.model.NewsCategoriesItem
import com.application.newsapp.model.NewsCategoriesResponse
import com.application.newsapp.model.NewsItem
import com.application.newsapp.model.NewsResponse
import com.application.newsapp.repository.MainRepository
import com.application.newsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var newsList: ArrayList<NewsItem> = ArrayList()
    lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    private var categories: ArrayList<NewsCategoriesItem> = ArrayList()
    private var selectedPosition = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel()
        mainRepository = MainRepository(
            this,
            mainViewModel
        )

        toolbar.title = "News"
        toolbar.navigationIcon = null
        setSupportActionBar(toolbar)

        val newsObserver = Observer<NewsResponse?> { response ->

            response?.let {

                newsList = it.items as ArrayList<NewsItem>

                //Update the orders adapter with filled list
                newsRecyclerViewAdapter =
                    NewsRecyclerViewAdapter(
                        this,
                        newsList
                    )
                newsRecyclerView.adapter = newsRecyclerViewAdapter

            }

        }

        mainViewModel.newsResponse.observe(this, newsObserver)

        val newsCategoriesObserver = Observer<NewsCategoriesResponse?> { response ->

            response?.let {

                if (categories.isEmpty()) {

                    categories = it.items as ArrayList<NewsCategoriesItem>

                    val categories = it.items?.size?.let { it1 -> Array(it1 + 1) { "n = $it" } }

                    categories?.set(0, "All")

                    for ((index, item) in it.items!!.withIndex()) {
                        categories?.set(index + 1, item.name.toString())
                    }

                    val adapter = categories?.let { it1 ->
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_spinner_item,
                            it1
                        )
                    }
                    adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                }

            }

        }

        mainViewModel.newsCategoriesResponse.observe(this, newsCategoriesObserver)

        mainRepository.getNews()

        newsRecyclerViewAdapter =
            NewsRecyclerViewAdapter(
                this,
                newsList
            )
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        newsRecyclerView.layoutManager = layoutManager
        newsRecyclerView.adapter = newsRecyclerViewAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> {
                        mainRepository.getNews()

                    }
                    1 -> {
                        categories[position - 1].id?.let { mainRepository.getNewsByCategory(it) }
                    }
                    2 -> {
                        categories[position - 1].id?.let { mainRepository.getNewsByCategory(it) }
                    }
                    3 -> {
                        categories[position - 1].id?.let { mainRepository.getNewsByCategory(it) }
                    }
                }

            }

        }

    }
}
