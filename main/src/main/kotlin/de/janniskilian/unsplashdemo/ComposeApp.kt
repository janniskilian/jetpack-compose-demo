package de.janniskilian.unsplashdemo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import de.janniskilian.unsplashdemo.feature.search.PhotosScreen
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalAnimationApi
@ExperimentalTime
@Preview
@Composable
fun ComposeApp() {
    UnsplashDemoTheme {
        PhotosScreen()
    }
}
