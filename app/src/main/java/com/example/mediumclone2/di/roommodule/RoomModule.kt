package com.example.mediumclone2.di.roommodule

import android.content.Context
import androidx.room.Room
import com.example.mediumclone2.room.UserDao
import com.example.mediumclone2.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDao(userDatabase: UserDatabase): UserDao {
            return userDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, UserDatabase.DB_NAME)
            .fallbackToDestructiveMigration().build()
    }
}