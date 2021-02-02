package com.example.mediumclone2.interfaces

import android.view.View
import com.example.mediumclone2.retrofit.models.articles.Article

interface HomeClickInterface {
    fun onClick(article: Article,view: View)
}