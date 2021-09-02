package de.janniskilian.unsplashdemo.components

import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.janniskilian.unsplashdemo.R
import de.janniskilian.unsplashdemo.theme.UnsplashDemoColors
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme

private const val ANIMATION_DURATION_MILLIS = 250
private const val ANIMATION_KEYFRAME_1_TIMESTAMP = 125
private const val ANIMATION_KEYFRAME_2_TIMESTAMP = 200

@Composable
fun LikeButton(
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    onLikeChange: (isLiked: Boolean) -> Unit = {}
) {
    val imageVector = if (isLiked) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    val transition = updateTransition(
        targetState = isLiked,
        label = stringResource(R.string.like_button_animation_label)
    )

    val scale by transition.animateFloat(
        transitionSpec = { likeButtonAnimationSpec() },
        label = stringResource(R.string.like_button_animation_label)
    ) { targetState ->
        when (targetState) {
            true -> 1f
            false -> 1f
        }
    }

    IconToggleButton(
        checked = isLiked,
        onCheckedChange = onLikeChange
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = stringResource(R.string.like_button_desc),
            tint = UnsplashDemoColors.red,
            modifier = modifier.scale(scale)
        )
    }
}

@Suppress("MagicNumber")
@Composable
private fun Transition.Segment<Boolean>.likeButtonAnimationSpec(): KeyframesSpec<Float> =
    when {
        false isTransitioningTo true ->
            keyframes {
                durationMillis = ANIMATION_DURATION_MILLIS
                1.4f at ANIMATION_KEYFRAME_1_TIMESTAMP
                0.9f at ANIMATION_KEYFRAME_2_TIMESTAMP
            }
        else ->
            keyframes {
                durationMillis = ANIMATION_DURATION_MILLIS
                0.6f at ANIMATION_KEYFRAME_1_TIMESTAMP
                1.1f at ANIMATION_KEYFRAME_2_TIMESTAMP
            }
    }

@Preview
@Composable
private fun LikeButtonPreview() {
    var isLiked by remember { mutableStateOf(true) }

    UnsplashDemoTheme {
        LikeButton(
            isLiked = isLiked,
            onLikeChange = { isLiked = it },
            modifier = Modifier.size(48.dp)
        )
    }
}
