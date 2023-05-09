package com.example.breakingnews

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class NewsListAdapter(private val listener: NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private val item : ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {// it is called no of times = no of views on the screen
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)//this converts view to xml
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClick(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {//it is called 1st time and tells how much item will remain in it.
        return item.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {//it binds data in the holder.
        val currItem = item[position]
        holder.titleView.text = currItem.title
        holder.author.text = currItem.author
        Glide.with(holder.titleView.context).load(currItem.imageUrl).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>) { // to provide items to adapter
        item.clear()
        item.addAll(updatedNews)

        notifyDataSetChanged()//it calls all three function again
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)

}

interface NewsItemClicked{//to tell that some item is clicked.
    fun onItemClick(item: News)
}