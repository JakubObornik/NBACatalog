package jakub.obornik.nbacatalog.core.di

import jakub.obornik.nbacatalog.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubscriptionKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val changed = original
            .newBuilder()
            .header("Authorization", BuildConfig.API_KEY)
            .build()
        return chain.proceed(changed)
    }
}