package com.example.mediumclone2.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("DELETE  FROM user_table")
    suspend fun delete()

    @Query("SELECT *FROM user_table")
    suspend fun getUsers():UserEntity
}