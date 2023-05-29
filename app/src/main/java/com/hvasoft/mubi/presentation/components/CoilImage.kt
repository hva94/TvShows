package com.hvasoft.mubi.presentation.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.disk.DiskCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.hvasoft.mubi.R

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    @StringRes imageDescriptionResId: Int?,
    imageDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val context = LocalContext.current
    val description = imageDescriptionResId?.let {
        stringResource(it)
    } ?: run {
        imageDescription
    }

    val imageLoader = ImageLoader.Builder(context)
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .build()
        }.build()

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .memoryCacheKey(imageUrl)
            .build(),
        placeholder = painterResource(R.drawable.ic_image),
        error = painterResource(R.drawable.ic_image_broken),
        contentDescription = description,
        contentScale = contentScale,
        modifier = modifier,
        imageLoader = imageLoader
    )
}

@Composable
@Preview(showBackground = true)
fun CoilImagePreview() {
    CoilImage(
        imageUrl = "https://image.tmdb.org/t/p/w200",
        imageDescription = null,
        imageDescriptionResId = null
    )
}
