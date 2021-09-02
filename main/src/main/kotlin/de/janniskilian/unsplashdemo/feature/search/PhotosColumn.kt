package de.janniskilian.unsplashdemo.feature.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import de.janniskilian.unsplashdemo.core.data.api.mockLatestPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun PhotosColumn(
    photos: StateFlow<PagingData<UnsplashPhoto>>,
    modifier: Modifier = Modifier,
    onLikeChange: (photo: UnsplashPhoto, isLiked: Boolean) -> Unit = { _, _ -> }
) {
    val items = photos.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items, key = { photo -> photo.id }) { photo ->
            if (photo != null) {
                PhotoItem(
                    photo = photo,
                    onLikeChange = { onLikeChange(photo, it) }
                )
            }
        }

        if (items.loadState.append.endOfPaginationReached) {
            item {
                PhotosColumnLoadingIndicator()
            }
        }
    }
}

@Composable
private fun PhotosColumnLoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        )
    }
}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PhotosColumnPreview() {
    val photos = mockLatestPhotosResponse

    UnsplashDemoTheme {
        PhotosColumn(photos = MutableStateFlow(PagingData.from(photos)))
    }
}
