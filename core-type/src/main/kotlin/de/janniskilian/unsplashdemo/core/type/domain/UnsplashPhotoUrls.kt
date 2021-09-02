package de.janniskilian.unsplashdemo.core.type.domain

import com.google.gson.annotations.SerializedName

data class UnsplashPhotoUrls(
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("small") val small: String,
    @SerializedName("thumb") val thumb: String
)
