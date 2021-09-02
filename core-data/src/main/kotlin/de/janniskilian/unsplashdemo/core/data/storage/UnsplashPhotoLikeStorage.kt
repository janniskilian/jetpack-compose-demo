package de.janniskilian.unsplashdemo.core.data.storage

import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import kotlinx.coroutines.flow.Flow

interface UnsplashPhotoLikeStorage {

    suspend fun setPhotoIsLiked(photo: UnsplashPhoto, isLiked: Boolean)

    val likedPhotoIds: Flow<Set<String>>
}
