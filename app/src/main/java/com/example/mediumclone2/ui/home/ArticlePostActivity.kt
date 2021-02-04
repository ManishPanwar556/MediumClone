package com.example.mediumclone2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mediumclone2.R
import com.example.mediumclone2.retrofit.models.postArticle.Article
import com.example.mediumclone2.retrofit.models.postArticle.ArticlePost
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlePostActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_post)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val postArticleBtn = findViewById<MaterialButton>(R.id.postArticleBtn)
        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val bodyEditText = findViewById<EditText>(R.id.bodyEditText)
        homeViewModel.postArticleSuccess.observe(this, Observer {
            if(it){
                Toast.makeText(this,"Article Posted SuccessFully",Toast.LENGTH_SHORT).show()
                finish()
            }
        })
        postArticleBtn.setOnClickListener {
            if (titleEditText.text.isNotEmpty() && descriptionEditText.text.isNotEmpty() && bodyEditText.text.isNotEmpty()) {
                val article = Article(
                    bodyEditText.text.toString(),
                    descriptionEditText.text.toString(),
                    listOf<String>(),
                    titleEditText.text.toString()
                )
                homeViewModel.postArticle(ArticlePost(article))
            } else {
                Toast.makeText(this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}