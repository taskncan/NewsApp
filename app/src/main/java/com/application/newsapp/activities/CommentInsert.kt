package com.application.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.newsapp.R
import com.application.newsapp.repository.MainRepository
import com.application.newsapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_comment_insert.*

class CommentInsert : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_insert)

        mainViewModel = MainViewModel()
        mainRepository = MainRepository(
            this,
            mainViewModel
        )

        toolbar.title = "Post Comments"
        toolbar.setNavigationOnClickListener {
            finish()
        }
        setSupportActionBar(toolbar)

        postComment.setOnClickListener {

            intent.getIntExtra("selectedNewsID",0)?.let {
                mainRepository.postComment(inputName.text.toString(),inputComment.text.toString(),it)
            }

        }
    }
}
