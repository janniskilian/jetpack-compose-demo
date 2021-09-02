package de.janniskilian.unsplashdemo.feature.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import de.janniskilian.unsplashdemo.R
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme

@ExperimentalAnimationApi
@Composable
fun PhotosSearchBar(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (input: String) -> Unit = {},
    onSearchAction: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = value,
        placeholder = {
            Text(text = stringResource(R.string.search_input_placeholder))
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_show_icon_desc)
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchAction() }
        ),
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .background(MaterialTheme.colors.background, MaterialTheme.shapes.small)
    )

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose {}
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PhotosSearchBarPreview() {
    UnsplashDemoTheme {
        PhotosSearchBar(value = "Test")
    }
}
