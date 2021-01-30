package com.example.mediumclone2.di.retrofitmodule

import com.example.mediumclone2.retrofit.api.MediumApiService
import com.example.mediumclone2.retrofit.api.MediumAuthenticationService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
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
    fun provideMediumApiService(retrofit: Retrofit.Builder): MediumApiService {
        return retrofit.build()
            .create(MediumApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit.Builder): MediumAuthenticationService {
        return retrofit.build().create(MediumAuthenticationService::class.java)
    }
}