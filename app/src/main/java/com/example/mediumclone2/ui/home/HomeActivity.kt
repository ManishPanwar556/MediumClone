package com.example.mediumclone2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mediumclone2.R
import com.example.mediumclone2.interfaces.HomeClickInterface
import com.example.mediumclone2.retrofit.models.articles.Article
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(),HomeClickInterface {
private val viewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeAdapter=HomeRecyclerAdapter(this)
        val rev=findViewById<RecyclerView>(R.id.homeRev)
        val progressBar=findViewById<ProgressBar>(R.id.homeProgressBar)
        rev.adapter=homeAdapter
        rev.layoutManager=LinearLayoutManager(this)
        viewModel.articles.observe(this, Observer {
            if(it.isNotEmpty()){
                homeAdapter.setData(it)
                rev.visibility= View.VISIBLE
                progressBar.visibility=View.GONE
            }
        })
    }

    override fun onClick(article: Article,view: View) {
        val activityOptionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(this,view,"myTransition")
        val intent= Intent(this,ArticleActivity::class.java)
        intent.putExtra("title",article.title)
        intent.putExtra("description",article.description)
        intent.putExtra("createdAt",article.createdAt)
        intent.putExtra("profile",article.author.image)
        intent.putExtra("userName",article.author.username)
        startActivity(intent,activityOptionsCompat.toBundle())
    }

}