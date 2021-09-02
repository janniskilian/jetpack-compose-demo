package de.janniskilian.unsplashdemo.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.janniskilian.unsplashdemo.R
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme

@Composable
fun PhotosBottomAppBar(
    isSearchShown: Boolean,
    onSearchButtonClick: () -> Unit = {}
) {
    BottomAppBar {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.weight(1f)
                )

                val imageVector: ImageVector
                val contentDescription: String
                if (isSearchShown) {
                    imageVector = Icons.Default.Clear
                    contentDescription = stringResource(R.string.search_show_icon_desc)
                } else {
                    imageVector = Icons.Default.Search
                    contentDescription = stringResource(R.string.search_hide_icon_desc)
                }
                IconButton(
                    onClick = onSearchButtonClick,
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = contentDescription
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotosBottomAppBarPreview() {
    UnsplashDemoTheme {
        PhotosBottomAppBar(isSearchShown = true)
    }
}
