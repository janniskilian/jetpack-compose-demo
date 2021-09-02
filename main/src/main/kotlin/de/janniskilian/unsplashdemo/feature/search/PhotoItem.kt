package de.janniskilian.unsplashdemo.feature.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import de.janniskilian.unsplashdemo.components.LikeButton
import de.janniskilian.unsplashdemo.components.VerticalCounter
import de.janniskilian.unsplashdemo.core.data.api.mockLatestPhotosResponse
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashUser
import de.janniskilian.unsplashdemo.theme.UnsplashDemoTheme
import java.text.DateFormat

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun PhotoItem(
    photo: UnsplashPhoto,
    modifier: Modifier = Modifier,
    onLikeChange: (isLiked: Boolean) -> Unit = {}
) {
    Card(
        elevation = 6.dp,
        modifier = modifier
    ) {
        ConstraintLayout {
            val (imageRef,
                userProfileRef,
                likesRef,
                descriptionRef
            ) = createRefs()

            PhotoImage(
                photo = photo,
                modifier = Modifier
                    .constrainAs(imageRef) {
                        width = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .aspectRatio(photo.aspectRatio.toFloat())
            )

            UserProfile(
                user = photo.user,
                modifier = Modifier.constrainAs(userProfileRef) {
                    width = Dimension.fillToConstraints
                    top.linkTo(imageRef.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(likesRef.start, 16.dp)
                }
            )

            PhotoLikes(
                likes = photo.totalLikes,
                isLiked = photo.isLiked ?: false,
                onLikeChange = onLikeChange,
                modifier = Modifier.constrainAs(likesRef) {
                    end.linkTo(parent.end, 8.dp)
                    top.linkTo(imageRef.bottom, 8.dp)
                }
            )

            PhotoDescription(
                photo = photo,
                modifier = Modifier.constrainAs(descriptionRef) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(userProfileRef.bottom, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                }
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun PhotoImage(
    photo: UnsplashPhoto,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberImagePainter(
            data = photo.urls.regular,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = photo.altDescription,
        modifier = modifier
            .background(Color(android.graphics.Color.parseColor(photo.colorHex)))
    )
}

@ExperimentalCoilApi
@Composable
fun UserProfile(user: UnsplashUser, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        UserProfileImage(
            user = user,
            modifier = Modifier.size(32.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .heightIn(min = 32.dp)
                .padding(start = 8.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.body1
            )

            val location = user.location
            if (!location.isNullOrBlank()) {
                Text(
                    text = location,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun UserProfileImage(user: UnsplashUser, modifier: Modifier = Modifier) {
    Image(
        painter = rememberImagePainter(
            data = user.profileImageUrls.medium,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = user.username,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@ExperimentalAnimationApi
@Composable
fun PhotoLikes(
    likes: Int,
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    onLikeChange: (isLiked: Boolean) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        VerticalCounter(count = likes)
        LikeButton(
            isLiked = isLiked,
            onLikeChange = onLikeChange,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun PhotoDescription(photo: UnsplashPhoto, modifier: Modifier = Modifier) {
    val dateFormat = remember {
        DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT)
    }

    Column(modifier = modifier) {
        Text(
            text = dateFormat.format(photo.creationDate),
            style = MaterialTheme.typography.caption
        )

        val description = photo.description
        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun PhotoItemPreview() {
    val photo = mockLatestPhotosResponse[0]

    UnsplashDemoTheme {
        PhotoItem(photo = photo)
    }
}
