package com.example.roomhomeworkdefaultrepo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhomeworkdefaultrepo.databinding.ItemPostBinding
import com.example.roomhomeworkdefaultrepo.room.AppDatabase
import com.example.roomhomeworkdefaultrepo.room.dto.PostDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostRecyclerAdapter(val context: Context): RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {
    var posts: List<PostDTO> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostRecyclerAdapter.ViewHolder {
        return ViewHolder(ItemPostBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent,false)))
    }

    override fun onBindViewHolder(holder: PostRecyclerAdapter.ViewHolder, position: Int) {
        holder.binding.postdto = posts[position]
        holder.binding.itemAll.setOnClickListener {
            val db = AppDatabase.getInstance(context)!!
            CoroutineScope(Dispatchers.IO).launch {
                db.postDAO().deletePostById(posts[position].id)
                val dt = db.postDAO().getAllPosts()
                withContext(Dispatchers.Main) {
                    posts =dt
                    notifyDataSetChanged()
                }
            }
        }
    }


    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {

    }


}