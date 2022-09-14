package com.application.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.application.newsapp.R
import com.application.newsapp.model.NewsItem
import com.application.newsapp.model.NewsResponse
import com.application.newsapp.repository.MainRepository
import com.application.newsapp.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_detail.toolbar

class NewsDetail : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    lateinit var newsItem : NewsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        toolbar.title = "News Details"
        setSupportActionBar(toolbar)

        mainViewModel = MainViewModel()
        mainRepository = MainRepository(
            this,
            mainViewModel
        )

        val newsObserver = Observer<NewsResponse?> { response ->

            response?.let {

                newsItem = it.items?.get(0)!!

                Glide.with(this)
                    .load(newsItem?.image)
                    .into(newsImageView)

                newsTitle.text = newsItem?.title
                newsDate.text = newsItem?.date.toString()
                newsDescription.text = newsItem?.text.toString()

            }

        }

        mainViewModel.newsDetailResponse.observe(this, newsObserver)

        intent.getIntExtra("selectedNewsID",0)?.let {
            mainRepository.getNewsByID(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.newsdetail,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_comment) {

            val i = Intent(this, CommentActivity::class.java)
            i.putExtra("selectedNewsID", newsItem.id)
            startActivity(i)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
