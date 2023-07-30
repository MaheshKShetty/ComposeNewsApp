package com.cap.samplecompose.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @Named("HttpLoggingInterceptor2")
    fun getInterceptor() : HttpLoggingInterceptor  {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(@Named("HttpLoggingInterceptor2") interceptor:HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder().addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun gsonProvider() :GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    @Named("HttpLoggingInterceptor1")
    fun httpLogging():HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun retrofitInstance(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory):Retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): APIInterface =
        retrofit.create(APIInterface::class.java)


}