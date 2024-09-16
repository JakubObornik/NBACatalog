package jakub.obornik.nbacatalog.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakub.obornik.nbacatalog.core.network.BallDontLieApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.balldontlie.io/v1/"

    @Provides
    @Singleton
    internal fun provideOkHttp(
        subscriptionKeyInterceptor: SubscriptionKeyInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(subscriptionKeyInterceptor)
            .build()
    }

    @Provides
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    internal fun provideApiServices(retrofit: Retrofit) =
        retrofit.create(BallDontLieApiService::class.java)
}