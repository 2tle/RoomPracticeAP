package com.example.roomhomeworkdefaultrepo.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PostDTO (
    var title: String,
    var content: String
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0


}