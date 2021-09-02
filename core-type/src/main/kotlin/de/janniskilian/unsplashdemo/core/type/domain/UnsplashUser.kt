package de.janniskilian.unsplashdemo.core.type.domain

import com.google.gson.annotations.SerializedName

data class UnsplashUser(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("bio") val bio: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("profile_image") val profileImageUrls: UnsplashUserProfileImageUrls,
)
