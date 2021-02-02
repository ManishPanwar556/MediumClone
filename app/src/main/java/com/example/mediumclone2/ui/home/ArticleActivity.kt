package com.example.mediumclone2.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mediumclone2.R

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val userName=intent.getStringExtra("userName")
        val description=intent.getStringExtra("description")
        val profileUrl=intent.getStringExtra("profile")
        val title=intent.getStringExtra("title")
        val imageView=findViewById<ImageView>(R.id.profile)
        val titleTextView=findViewById<TextView>(R.id.title)
        val descriptionTextView=findViewById<TextView>(R.id.description)
        Glide.with(this).load(profileUrl).into(imageView)
        titleTextView.text=title
        descriptionTextView.text=description
    }

}