package com.ceocoding.cineaux.di

import android.app.Application
import androidx.room.Room
import com.ceocoding.cineaux.data.local.WatchListDatabase
import com.ceocoding.cineaux.data.remote.ApiService
import com.ceocoding.cineaux.data.repository.FilmRepositoryImpl
import com.ceocoding.cineaux.data.repository.WatchListRepositoryImpl
import com.ceocoding.cineaux.domain.repository.FilmRepository
import com.ceocoding.cineaux.domain.repository.WatchListRepository
import com.ceocoding.cineaux.domain.use_case.GetFilmData
import com.ceocoding.cineaux.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideTmbdApi(): ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmRepository(
        api: ApiService
    ): FilmRepository {
        return FilmRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetFilmDataUseCase(repository: FilmRepository): GetFilmData {
        return GetFilmData(repository)
    }

    @Provides
    @Singleton
    fun provideWatchListRepository(db: WatchListDatabase): WatchListRepository {
        return WatchListRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideWatchListDatabase(app: Application): WatchListDatabase {
        return Room.databaseBuilder(
            app,
            WatchListDatabase::class.java,
            "watch_list_db"
        ).build()
    }

}