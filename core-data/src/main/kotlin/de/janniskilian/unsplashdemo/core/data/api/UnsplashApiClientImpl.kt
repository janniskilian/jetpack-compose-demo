package de.janniskilian.core.data.unsplash

import android.content.Context
import de.janniskilian.core.data.R
import de.janniskilian.unsplashdemo.core.type.api.UnsplashSearchPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UnsplashApiClientImpl(private val context: Context) : UnsplashApiClient {

    private val baseUrl = "https://api.unsplash.com/"
    private val key = context.getString(R.string.unsplash_api_access_key)
    private val authorizationHeaderValue = "$AUTHORIZATION_CLIENT_ID $key"

    private val api = createApi()

    private fun createApi() = Retrofit
        .Builder()
        .client(createOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<UnsplashApi>()

    override suspend fun getLatestPhotos(
        page: Int,
        photosPerPage: Int
    ): List<UnsplashPhoto> =
        api.getPhotos(
            page = page,
            photosPerPage = photosPerPage,
            orderBy = ORDER_BY_LATEST
        )

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        photosPerPage: Int
    ): UnsplashSearchPhotosResponse =
        api.getSearchPhotos(
            query = query,
            page = page,
            photosPerPage = photosPerPage,
            contentFilter = CONTENT_FILTER_HIGH
        )

    private fun createOkHttpClient(): OkHttpClient {
        val cache = Cache(context.cacheDir, CACHE_SIZE_BYTES)
        val builder = OkHttpClient
            .Builder()
            .cache(cache)
            .addInterceptor { requestHeadersInterceptor(it) }

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addInterceptor(httpLoggingInterceptor)

        return builder.build()
    }

    private fun requestHeadersInterceptor(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .header(HEADER_AUTHORIZATION, authorizationHeaderValue)
            .build()

        return chain.proceed(request)
    }

    companion object {

        private const val CACHE_SIZE_BYTES = 1024L * 1024L

        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val AUTHORIZATION_CLIENT_ID = "Client-ID"

        private const val ORDER_BY_LATEST = "latest"
        private const val CONTENT_FILTER_HIGH = "high"
    }
}
