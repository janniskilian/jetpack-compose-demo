package de.janniskilian.core.data.unsplash

import de.janniskilian.unsplashdemo.core.type.api.UnsplashSearchPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("perPage") photosPerPage: Int,
        @Query("order_by") orderBy: String
    ): List<UnsplashPhoto>

    @GET("search/photos")
    suspend fun getSearchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("perPage") photosPerPage: Int,
        @Query("contentFilter") contentFilter: String
    ): UnsplashSearchPhotosResponse
}
