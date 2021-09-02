package de.janniskilian.unsplashdemo.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.janniskilian.core.data.unsplash.UnsplashApiClient
import de.janniskilian.core.data.unsplash.UnsplashApiClientImpl
import de.janniskilian.core.data.unsplash.UnsplashLatestPhotosPagingSource
import de.janniskilian.unsplashdemo.core.data.storage.UnsplashPhotoLikeStorage
import de.janniskilian.unsplashdemo.core.data.storage.UnsplashPhotoLikeStorageImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideUnsplashApiClient(@ApplicationContext context: Context): UnsplashApiClient =
        UnsplashApiClientImpl(context)

    @Singleton
    @Provides
    fun provideUnsplashLatestPhotosPagingSource(
        unsplashApiClient: UnsplashApiClient
    ): UnsplashLatestPhotosPagingSource =
        UnsplashLatestPhotosPagingSource(unsplashApiClient)

    @Singleton
    @Provides
    fun provideUnsplashPhotoLikeStorage(
        @ApplicationContext context: Context
    ): UnsplashPhotoLikeStorage =
        UnsplashPhotoLikeStorageImpl(context.defaultPreferencesDataStore)
}

private const val DEFAULT_PREFERENCES_DATA_STORE_NAME = "default"
private val Context.defaultPreferencesDataStore: DataStore<Preferences>
        by preferencesDataStore(name = DEFAULT_PREFERENCES_DATA_STORE_NAME)
