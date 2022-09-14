package com.application.newsapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.newsapp.R
import com.application.newsapp.adapter.CommentsRecyclerViewAdapter
import com.application.newsapp.model.CommentsItem
import com.application.newsapp.model.CommentsResponse
import com.application.newsapp.model.NewsItem
import com.application.newsapp.repository.MainRepository
import com.application.newsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {

    private var commentsList: ArrayList<CommentsItem> = ArrayList()
    lateinit var commentsRecyclerViewAdapter: CommentsRecyclerViewAdapter
    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    var newsItemID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        mainViewModel = MainViewModel()
        mainRepository = MainRepository(
            this,
            mainViewModel
        )

        toolbar.title = "Comments"
        toolbar.setNavigationOnClickListener {
            finish()
        }
        setSupportActionBar(toolbar)

        val commentsObserver = Observer<CommentsResponse?> { response ->

            response?.let {

                commentsList = it.items as ArrayList<CommentsItem>

                //Update the orders adapter with filled list
                commentsRecyclerViewAdapter =
                    CommentsRecyclerViewAdapter(
                        this,
                        commentsList
                    )
                commentsRecyclerView.adapter = commentsRecyclerViewAdapter

            }

        }

        mainViewModel.commentsResponse.observe(this, commentsObserver)

        intent.getIntExtra("selectedNewsID",0)?.let {
            newsItemID = it
            mainRepository.getComments(it)
        }

        commentsRecyclerViewAdapter =
            CommentsRecyclerViewAdapter(
                this,
                commentsList
            )
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        commentsRecyclerView.layoutManager = layoutManager
        commentsRecyclerView.adapter = commentsRecyclerViewAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.comments,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_comment) {

            val i = Intent(this, CommentInsert::class.java)
            i.putExtra("selectedNewsID", newsItemID)
            startActivity(i)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
