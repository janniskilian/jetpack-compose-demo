package de.janniskilian.unsplashdemo.core.data.api

import com.google.gson.internal.bind.util.ISO8601Utils
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhoto
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashPhotoUrls
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashUser
import de.janniskilian.unsplashdemo.core.type.domain.UnsplashUserProfileImageUrls
import java.text.ParsePosition
import java.util.*

private fun parseDate(timestamp: String): Date =
    ISO8601Utils.parse(timestamp, ParsePosition(0))

@Suppress("MaxLineLength")
val mockLatestPhotosResponse = listOf(
    UnsplashPhoto(
        id = "e7yjYr8GxN0",
        creationDate = parseDate("2020-07-01T18:30:14-04:00"),
        width = 4500,
        height = 3000,
        colorHex = "#737359",
        likes = 18,
        isLiked = null,
        primaryDescription = null,
        altDescription = "brown cookies on white ceramic bowl",
        urls = UnsplashPhotoUrls(
            full = "https://images.unsplash.com/photo-1627662168223-7df99068099a?crop=entropy\u0026cs=srgb\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyODQwODAwNQ\u0026ixlib=rb-1.2.1\u0026q=85",
            regular = "https://images.unsplash.com/photo-1627662168223-7df99068099a?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyODQwODAwNQ\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=1080",
            small = "https://images.unsplash.com/photo-1627662168223-7df99068099a?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyODQwODAwNQ\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=400",
            thumb = "https://images.unsplash.com/photo-1627662168223-7df99068099a?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MXwxfGFsbHwxfHx8fHx8Mnx8MTYyODQwODAwNQ\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=200"
        ),
        user = UnsplashUser(
            id = "GA8BER4Fd2U",
            username = "davegoudreau",
            name = "Dave Goudreau",
            bio = "Street-Urban-Portrait Photographer.\r\n",
            location = "Qc, Canada",
            profileImageUrls = UnsplashUserProfileImageUrls(
                small = "https://images.unsplash.com/profile-1586154327904-e11abd5d7513image?ixlib=rb-1.2.1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=32\u0026w=32",
                medium = "https://images.unsplash.com/profile-1586154327904-e11abd5d7513image?ixlib=rb-1.2.1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=64\u0026w=64",
                large = "https://images.unsplash.com/profile-1586154327904-e11abd5d7513image?ixlib=rb-1.2.1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=128\u0026w=128"
            )
        )
    ),
    UnsplashPhoto(
        id = "2DC3GyeqWjI",
        creationDate = parseDate("2020-10-23T18:43:47-04:00"),
        width = 4000,
        height = 6000,
        colorHex = "#59400c",
        likes = 24,
        isLiked = null,
        primaryDescription = null,
        altDescription = "purple flower field during daytime",
        urls = UnsplashPhotoUrls(
            full = "https://images.unsplash.com/photo-1603492501465-2d1bf970b974?crop=entropy\u0026cs=srgb\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MHwxfGFsbHwyfHx8fHx8Mnx8MTYyODQwOTYyMg\u0026ixlib=rb-1.2.1\u0026q=85",
            regular = "https://images.unsplash.com/photo-1603492501465-2d1bf970b974?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MHwxfGFsbHwyfHx8fHx8Mnx8MTYyODQwOTYyMg\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=1080",
            small = "https://images.unsplash.com/photo-1603492501465-2d1bf970b974?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MHwxfGFsbHwyfHx8fHx8Mnx8MTYyODQwOTYyMg\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=400",
            thumb = "https://images.unsplash.com/photo-1603492501465-2d1bf970b974?crop=entropy\u0026cs=tinysrgb\u0026fit=max\u0026fm=jpg\u0026ixid=MnwyNTIwOTV8MHwxfGFsbHwyfHx8fHx8Mnx8MTYyODQwOTYyMg\u0026ixlib=rb-1.2.1\u0026q=80\u0026w=200"
        ),
        user = UnsplashUser(
            id = "2MlY3dswl7o",
            username = "jack_skinner",
            name = "Jack Skinner",
            bio = null,
            location = "Hertfordshire, UK",
            profileImageUrls = UnsplashUserProfileImageUrls(
                small = "https://images.unsplash.com/profile-1603492096078-1c4c6150c3fcimage?ixlib=rb-1.2.1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=32\u0026w=32",
                medium = "https://images.unsplash.com/profile-1603492096078-1c4c6150c3fcimage?ixlib=rb-1.2.1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=64\u0026w=64",
                large = "https://images.unsplash.com/profile-1603492096078-1c4c6150c3fcimage?ixlib=rb-1.2  .1\u0026q=80\u0026fm=jpg\u0026crop=faces\u0026cs=tinysrgb\u0026fit=crop\u0026h=128\u0026w=128"
            )
        )
    )
)
