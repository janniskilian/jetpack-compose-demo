package de.janniskilian.unsplashdemo.core.type.api

import com.google.gson.annotations.SerializedName
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto

data class UnsplashSearchPhotosResponse(
    @SerializedName("total") val totalPhotos: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val photos: List<UnsplashPhoto>
)
