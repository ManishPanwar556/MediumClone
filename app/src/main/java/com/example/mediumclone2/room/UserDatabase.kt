package com.example.mediumclone2.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class],version = 1)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao():UserDao
    companion object{
        const val DB_NAME="userDb"
    }
}