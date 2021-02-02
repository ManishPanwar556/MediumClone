package com.example.mediumclone2.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val token: String,
    val name: String,
    val bio: String?,
    val profileUrl: String?,
    val email: String
)
