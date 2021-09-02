package de.janniskilian.unsplashdemo.core.type.domain

import com.google.gson.annotations.SerializedName

data class UnsplashUserProfileImageUrls(
    @SerializedName("small") val small: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("large") val large: String
)
