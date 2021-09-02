package de.janniskilian.core.data.unsplash

import de.janniskilian.unsplashdemo.core.type.api.UnsplashSearchPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto

interface UnsplashApiClient {

    suspend fun getLatestPhotos(
        page: Int,
        photosPerPage: Int
    ): List<UnsplashPhoto>

    suspend fun searchPhotos(
        query: String,
        page: Int,
        photosPerPage: Int
    ): UnsplashSearchPhotosResponse
}
