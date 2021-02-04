package com.example.mediumclone2.ui.articlesFeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mediumclone2.R
import com.example.mediumclone2.retrofit.models.articles.Article
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class ArticlesRecyclerAdapter:RecyclerView.Adapter<ArticlesRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view: View):RecyclerView.ViewHolder(view){

    }
    private val oldData = ArrayList<Article>()

    class HomeDiffCallBack(
        private val oldList: List<Article>,
        private val newList: List<Article>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition].title == oldList[oldItemPosition].title

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList === oldList

    }

    fun setData(newList: List<Article>) {
        val diffCallBack = HomeDiffCallBack(oldData, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        oldData.clear()
        oldData.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val nameTextView = holder.view.findViewById<TextView>(R.id.posterName)
        val titleTextView = holder.view.findViewById<TextView>(R.id.homeTitle)
        val descriptionTextView = holder.view.findViewById<TextView>(R.id.homeDescription)
        val profile = holder.view.findViewById<ImageView>(R.id.userProfile)
        val createdAt=holder.view.findViewById<TextView>(R.id.postDate)
        nameTextView.text = oldData[position].author.username
        titleTextView.text = oldData[position].title
        descriptionTextView.text = oldData[position].description
        Glide.with(holder.view).load(oldData[position].author.image).into(profile)
        createdAt.text=oldData[position].createdAt

    }

    override fun getItemCount() = oldData.size
}