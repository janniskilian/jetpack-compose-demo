package de.janniskilian.unsplashdemo.core.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UnsplashPhotoLikeStorageImpl(
    private val defaultPreferencesDataStore: DataStore<Preferences>
) : UnsplashPhotoLikeStorage {

    private val likedPhotoIdsKey = stringSetPreferencesKey(KEY_LIKED_PHOTO_IDS)

    override suspend fun setPhotoIsLiked(photo: UnsplashPhoto, isLiked: Boolean) {
        defaultPreferencesDataStore.edit {
            val likedIds = it[likedPhotoIdsKey].orEmpty()

            it[likedPhotoIdsKey] = if (photo.id in likedIds) {
                likedIds - photo.id
            } else {
                likedIds + photo.id
            }
        }
    }

    override val likedPhotoIds: Flow<Set<String>>
        get() = defaultPreferencesDataStore
            .data
            .map {
                it[likedPhotoIdsKey] ?: emptySet()
            }

    companion object {

        private const val KEY_LIKED_PHOTO_IDS = "KEY_LIKED_PHOTO_IDS"
    }
}
