package de.janniskilian.unsplashdemo.core.type.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class UnsplashPhoto(
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val creationDate: Date,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("color") val colorHex: String,
    @SerializedName("likes") val likes: Int,
    val isLiked: Boolean?,
    @SerializedName("description") val primaryDescription: String?,
    @SerializedName("alt_description") val altDescription: String?,
    @SerializedName("urls") val urls: UnsplashPhotoUrls,
    @SerializedName("user") val user: UnsplashUser
) {

    val description get() = primaryDescription ?: altDescription

    val aspectRatio: Double
        get() = width.toDouble() / height

    val totalLikes
        get() = if (isLiked == true) {
            likes + 1
        } else {
            likes
        }
}
