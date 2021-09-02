package de.janniskilian.unsplashdemo.feature.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import coil.annotation.ExperimentalCoilApi
import de.janniskilian.unsplashdemo.core.data.api.mockLatestPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.ExperimentalTime

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@FlowPreview
@ExperimentalTime
@Composable
fun PhotosScreen(viewModel: PhotosViewModel = hiltViewModel()) {
    val isSearchBarShown by viewModel.isSearchBarShown.collectAsState()
    val searchInput by viewModel.searchInput.collectAsState()

    PhotosContent(
        isSearchBarShown = isSearchBarShown,
        searchBarInput = searchInput,
        photos = viewModel.photos,
        onSearchButtonClick = viewModel::onSearchButtonClick,
        onSearchInputChange = viewModel::setSearchInput,
        onLikeChange = viewModel::setIsPhotoLiked
    )
}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
private fun PhotosContent(
    isSearchBarShown: Boolean,
    searchBarInput: String,
    photos: StateFlow<PagingData<UnsplashPhoto>>,
    onSearchButtonClick: () -> Unit = {},
    onSearchInputChange: (input: String) -> Unit = {},
    onLikeChange: (photo: UnsplashPhoto, isLiked: Boolean) -> Unit = { _, _ -> }
) {
    Scaffold(
        bottomBar = {
            PhotosBottomAppBar(
                isSearchShown = isSearchBarShown,
                onSearchButtonClick = onSearchButtonClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PhotosColumn(
                photos = photos,
                onLikeChange = onLikeChange
            )

            AnimatedVisibility(
                visible = isSearchBarShown,
                enter = slideIn(initialOffset = { IntOffset(0, it.height) }),
                exit = slideOut(targetOffset = { IntOffset(0, it.height) }),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                PhotosSearchBar(
                    value = searchBarInput,
                    onValueChange = onSearchInputChange,
                    onSearchAction = onSearchButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PhotosContentPreview() {
    val photos = mockLatestPhotosResponse

    UnsplashDemoTheme {
        PhotosContent(
            isSearchBarShown = true,
            searchBarInput = "Munich",
            photos = MutableStateFlow(PagingData.from(photos))
        )
    }
}
