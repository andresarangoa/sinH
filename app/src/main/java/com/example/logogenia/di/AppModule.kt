package com.example.logogenia.di

import android.app.Application
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.room.Room
import com.example.data.RepositoryImp
import com.example.domain.databasemanager.MaterialDatabase
import com.example.domain.databasemanager.WordDao
import com.example.domain.databasemanager.repository.ApiConstants
import com.example.domain.databasemanager.repository.MaterialRepository
import com.example.logogenia.BuildConfig
<<<<<<< Updated upstream
=======
import com.example.logogenia.presentation.ui.knowingWords.KnowingWordsRoute
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
>>>>>>> Stashed changes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val databaseName = "material_db"
    @Provides
    fun provideContext(
        app: Application
    ): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext app: Context
    ): MaterialDatabase = Room.databaseBuilder(
        app,
        MaterialDatabase::class.java,
        databaseName
    ).build()

    @Provides
    @Singleton
    fun provideWordDetailDao(db: MaterialDatabase): WordDao = db.wordDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.URL_REQUEST)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideVideoPlayer(app: Application): Player{
        return ExoPlayer.Builder(app)
            .build()
    }

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext app: Context): FirebaseAnalytics{
        return Firebase.analytics
    }

    @Provides
    @Singleton
    fun provideMaterialRepository(dataSource: RepositoryImp): MaterialRepository = dataSource

}