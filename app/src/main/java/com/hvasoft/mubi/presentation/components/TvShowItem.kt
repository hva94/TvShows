package com.hvasoft.mubi.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.hvasoft.mubi.domain.model.TvShow
import com.hvasoft.mubi.presentation.theme.COMMON_PADDING
import com.hvasoft.mubi.presentation.theme.PADDING_8
import com.hvasoft.mubi.presentation.theme.backgroundCardColor
import com.hvasoft.mubi.presentation.theme.textColor
import com.hvasoft.mubi.presentation.util.constants.HomeConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowItem(tvShow: TvShow, onClickItem: (tvShow: TvShow) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = HomeConstants.ITEM_DEFAULT_ELEVATION
        ),
        shape = ShapeDefaults.Medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.backgroundCardColor
        ),
        onClick = { onClickItem(tvShow) },
        modifier = Modifier
            .width(HomeConstants.ITEM_CARD_WIDTH)
            .height(HomeConstants.ITEM_CARD_HEIGHT)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(HomeConstants.THUMBNAIL_HEIGHT),
                imageUrl = "${HomeConstants.BASE_URL_IMAGES_THUMBNAIL}${tvShow.posterPath}",
                imageDescriptionResId = null,
                imageDescription = tvShow.name,
                contentScale = ContentScale.Crop
            )
            Text(
                text = tvShow.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = COMMON_PADDING,
                        end = COMMON_PADDING,
                        top = HomeConstants.ITEM_TITLE_TOP_PADDING,
                        bottom = HomeConstants.ITEM_TITLE_BOTTOM_PADDING
                    ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.textColor,
                style = MaterialTheme.typography.titleSmall
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = COMMON_PADDING, end = COMMON_PADDING, bottom = COMMON_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    rating = tvShow.voteAverage,
                    stars = 5
                )
                Text(
                    text = if (tvShow.voteAverage > HomeConstants.MAXIMUM_AVERAGE) {
                        "${HomeConstants.MAXIMUM_AVERAGE}"
                    } else {
                        "${tvShow.voteAverage}"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.textColor,
                    modifier = Modifier.padding(start = PADDING_8)
                )
            }
        }
    }
}

@Composable
@Preview
fun TvItemPreview() {
    TvShowItem(
        tvShow = TvShow(
            backdropPath = "",
            firstAirDate = "",
            id = "",
            name = "SpongeBob",
            originalLanguage = "",
            originalName = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            voteAverage = 4.5,
            voteCount = 0,
            isFavorite = false,
            category = "",
            seasons = emptyList()
        ),
        onClickItem = {}
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TvItemPreviewDark() {
    TvShowItem(
        tvShow = TvShow(
            backdropPath = "",
            firstAirDate = "",
            id = "",
            name = "Big Hero 6",
            originalLanguage = "",
            originalName = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            voteAverage = 5.0,
            voteCount = 0,
            isFavorite = false,
            category = "",
            seasons = emptyList()
        ),
        onClickItem = {}
    )
}