package com.example.mediumclone2.di.retrofitmodule

import com.example.mediumclone2.retrofit.api.MediumApiService
import com.example.mediumclone2.retrofit.api.MediumAuthenticationService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://conduit.productionready.io/api/")
    }

    @Provides
    @Singleton
    fun provideMediumApiService(gson:Gson,retrofit: Retrofit.Builder): MediumApiService {
        return retrofit.addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(MediumApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(gson:Gson,retrofit: Retrofit.Builder): MediumAuthenticationService {
        return retrofit.addConverterFactory(GsonConverterFactory.create(gson)).build().create(MediumAuthenticationService::class.java)
    }
}