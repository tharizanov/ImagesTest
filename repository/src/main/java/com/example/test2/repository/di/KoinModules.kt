package com.example.test2.repository.di

import com.example.test2.repository.BuildConfig
import com.example.test2.repository.ImagesApi
import com.example.test2.repository.ImagesRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideImagesApi(get()) }
    single { ImagesRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideImagesApi(retrofit: Retrofit): ImagesApi = retrofit.create(ImagesApi::class.java)