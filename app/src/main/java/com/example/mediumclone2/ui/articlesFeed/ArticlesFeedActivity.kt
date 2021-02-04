package com.example.mediumclone2.ui.articlesFeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediumclone2.R
import com.example.mediumclone2.ui.home.HomeRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class ArticlesFeedActivity : AppCompatActivity() {
    private val viewModel:FeedArticlesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_feed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val progressBar=findViewById<ProgressBar>(R.id.feedProgressBar)
        val rev=findViewById<RecyclerView>(R.id.feedArticlesRev)
        val textView=findViewById<TextView>(R.id.feedTextView)
        val adapter=ArticlesRecyclerAdapter()
        rev.adapter=adapter
        rev.layoutManager=LinearLayoutManager(this)
        viewModel.articles.observe(this, Observer {
            progressBar.visibility= View.GONE
            textView.visibility=View.GONE
            adapter.setData(it)
        })
        viewModel.empty.observe(this, Observer {
            if(it){
                textView.visibility=View.VISIBLE
                progressBar.visibility= View.GONE
                rev.visibility=View.GONE
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}