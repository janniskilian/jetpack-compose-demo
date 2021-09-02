package de.janniskilian.unsplashdemo.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import de.janniskilian.core.data.unsplash.UnsplashApiClient
import de.janniskilian.core.data.unsplash.UnsplashLatestPhotosPagingSource
import de.janniskilian.unsplashdemo.core.data.api.UnsplashSearchPhotosPagingSource
import de.janniskilian.unsplashdemo.core.data.storage.UnsplashPhotoLikeStorage
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val unsplashApiClient: UnsplashApiClient,
    private val unsplashLatestPhotosPagingSource: UnsplashLatestPhotosPagingSource,
    private val photoLikeStorage: UnsplashPhotoLikeStorage
) : ViewModel() {

    private val _isSearchBarShown = MutableStateFlow(false)
    val isSearchBarShown: StateFlow<Boolean> = _isSearchBarShown

    private val _searchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = _searchInput

    @FlowPreview
    @ExperimentalCoroutinesApi
    @ExperimentalTime
    private val _photos = searchInput
        .debounce(Duration.seconds(SEARCH_INPUT_DEBOUNCE_SECONDS))
        .flatMapLatest {
            if (it.isBlank()) {
                Pager(PagingConfig(PAGE_SIZE)) {
                    unsplashLatestPhotosPagingSource
                }
                    .flow
                    .cachedIn(viewModelScope)
            } else {
                Pager(PagingConfig(PAGE_SIZE)) {
                    UnsplashSearchPhotosPagingSource(unsplashApiClient, it)
                }
                    .flow
                    .cachedIn(viewModelScope)
            }
        }

    @ExperimentalCoroutinesApi
    @FlowPreview
    @ExperimentalTime
    val photos: StateFlow<PagingData<UnsplashPhoto>> =
        combine(_photos, photoLikeStorage.likedPhotoIds) { photosValue, likesValue ->
            photosValue.map { photos ->
                photos.copy(
                    isLiked = photos.id in likesValue
                )
            }
        }
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun onSearchButtonClick() {
        _isSearchBarShown.value = !_isSearchBarShown.value
    }

    fun setSearchInput(input: String) {
        _searchInput.value = input
    }

    fun setIsPhotoLiked(photo: UnsplashPhoto, isLiked: Boolean) {
        viewModelScope.launch {
            photoLikeStorage.setPhotoIsLiked(photo, isLiked)
        }
    }

    companion object {

        private const val PAGE_SIZE = 10
        private const val SEARCH_INPUT_DEBOUNCE_SECONDS = 0.5
    }
}
