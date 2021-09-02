package de.janniskilian.unsplashdemo.core.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.janniskilian.core.data.unsplash.UnsplashApiClient
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import okio.IOException
import retrofit2.HttpException

class UnsplashSearchPhotosPagingSource(
    private val unsplashApiClient: UnsplashApiClient,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val page = if (params is LoadParams.Append) {
            params.key
        } else {
            1
        }

        return try {
            val response = unsplashApiClient.searchPhotos(query, page, params.loadSize)

            val nextKey = if (response.photos.isEmpty() || page == response.totalPages) {
                null
            } else {
                page + 1
            }

            LoadResult.Page(
                data = response.photos,
                prevKey = params.key?.minus(1),
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
