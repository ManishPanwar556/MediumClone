package com.example.mediumclone2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mediumclone2.R
import com.example.mediumclone2.interfaces.HomeClickInterface
import com.example.mediumclone2.retrofit.models.articles.Article

class HomeRecyclerAdapter(var clickInterface: HomeClickInterface) : RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                if(adapterPosition!=RecyclerView.NO_POSITION) {
                    clickInterface.onClick(oldData[adapterPosition],view)
                }
            }
        }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val nameTextView = holder.view.findViewById<TextView>(R.id.posterName)
        val titleTextView = holder.view.findViewById<TextView>(R.id.homeTitle)
        val descriptionTextView = holder.view.findViewById<TextView>(R.id.homeDescription)
        val profile = holder.view.findViewById<ImageView>(R.id.userProfile)
        nameTextView.text = oldData[position].author.username
        titleTextView.text = oldData[position].title
        descriptionTextView.text = oldData[position].description
        Glide.with(holder.view).load(oldData[position].author.image).into(profile)
    }

    override fun getItemCount() = oldData.size


}