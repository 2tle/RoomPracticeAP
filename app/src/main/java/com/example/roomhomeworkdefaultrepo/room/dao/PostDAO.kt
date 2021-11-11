package com.example.roomhomeworkdefaultrepo.room.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomhomeworkdefaultrepo.room.dto.PostDTO

@Dao
interface PostDAO {
    @Query("SELECT * FROM PostDTO")
    fun getAllPosts(): List<PostDTO>

    @Insert
    fun insertPost(vararg post: PostDTO)

    @Update
    fun updatePost(post: PostDTO)

    @Query("DELETE FROM PostDTO WHERE id= :id")
    fun deletePostById(id: Int)

}