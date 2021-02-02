package com.example.mediumclone2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mediumclone2.R
import com.example.mediumclone2.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MediumViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        viewModel.getUser()
        viewModel.notNull.observe(this, Observer {
            if(it){
                val intent= Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

}