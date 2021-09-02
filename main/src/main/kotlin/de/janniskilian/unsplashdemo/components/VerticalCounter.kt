package de.janniskilian.unsplashdemo.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme

@ExperimentalAnimationApi
@Composable
fun VerticalCounter(
    count: Int,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.body1
) {
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically({ height -> -height }) + fadeIn() with
                        slideOutVertically({ height -> height }) + fadeOut()
            } else {
                slideInVertically({ height -> height }) + fadeIn() with
                        slideOutVertically({ height -> -height }) + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        },
        modifier = modifier
    ) { targetCount ->
        Text(
            text = targetCount.toString(),
            style = textStyle
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun VerticalCounterPreview() {
    var count by remember { mutableStateOf(1) }

    UnsplashDemoTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                IconButton(onClick = { count++ }) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { count = (count - 1).coerceAtLeast(0) }) {
                    Icon(
                        imageVector = Icons.Default.RemoveCircle,
                        contentDescription = null
                    )
                }
            }

            VerticalCounter(
                count = count,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
