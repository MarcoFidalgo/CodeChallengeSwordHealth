package com.marcofidalgo.codechallenge.main.di

//import com.marcofidalgo.feature.catslist.data.local.MyRoomDatabase
import com.marcofidalgo.codechallenge.BuildConfig
import com.marcofidalgo.codechallenge.main.Constants
import com.marcofidalgo.feature.catslist.data.remote.BreedsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

//    @Provides
//    @Singleton
//    fun myRoomDatabase(application: Application): MyRoomDatabase {
//        return Room.databaseBuilder(
//            application,
//            MyRoomDatabase::class.java,
//            Constants.DB_NAME
//        ).build()
//    }

//    @Provides
//    fun catBreedsDao(database: MyRoomDatabase): CatBreedsDao {
//        return database.catBreedsDao
//    }

    @Provides
    @Singleton
    fun retrofit(): Retrofit {
        val sslContext = SSLContext.getInstance("TLS")
        val trustManager = object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
        }
        sslContext.init(null, arrayOf(trustManager), SecureRandom())

        val httpClient = OkHttpClient.Builder().run {
            sslSocketFactory(sslContext.socketFactory, trustManager)
            hostnameVerifier { _, _ -> true }
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
            build()
        }

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
    }

    @Provides
    fun BreedsApiService(retrofit: Retrofit): BreedsApiService {
        return retrofit.create(BreedsApiService::class.java)
    }
}