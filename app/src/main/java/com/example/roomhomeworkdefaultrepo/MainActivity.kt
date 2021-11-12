package com.example.roomhomeworkdefaultrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.roomhomeworkdefaultrepo.databinding.ActivityMainBinding
import com.example.roomhomeworkdefaultrepo.room.AppDatabase
import com.example.roomhomeworkdefaultrepo.room.dto.PostDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var contentList : List<PostDTO>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        adapter = PostRecyclerAdapter(this)
        binding.memoRecycler.adapter = adapter
        //binding.memoRecycler.adapter.updatePost()

        db = AppDatabase.getInstance(applicationContext)!!
        loadPost()

    }

    fun loadPost() {
        CoroutineScope(Dispatchers.IO).launch {
            contentList = db.postDAO().getAllPosts()
            runOnUiThread {
                adapter.posts = contentList
                adapter.notifyDataSetChanged()
            }
        }
    }


    fun uploadPost() {
        val title = binding.titleEditText.text.toString()
        val content = binding.contentEditText.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            db.postDAO().insertPost(PostDTO(title, content))
            loadPost()
        }
    }

    fun deletePost(idx: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            db.postDAO().deletePostById(idx)
            loadPost()
        }
    }


}