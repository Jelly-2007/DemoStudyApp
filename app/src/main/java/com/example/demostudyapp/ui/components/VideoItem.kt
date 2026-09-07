package com.example.demostudyapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.demostudyapp.model.entity.VideoEntity

@Composable
fun VideoItem(
    videoEntity: VideoEntity
){
    val constraintSet = ConstraintSet{
        val title = createRefFor("title")
        val cover = createRefFor("cover")
        val type = createRefFor("type")
        val duration = createRefFor("duration")
        val divider = createRefFor("divider")

        constrain(cover){
            start.linkTo(parent.start)
            centerVerticallyTo(parent)
            width = Dimension.value(115.5.dp)
        }

        constrain(title){
            start.linkTo(cover.end, margin = 8.dp)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(type){
            start.linkTo(title.start)
            bottom.linkTo(parent.bottom)
        }

        constrain(duration){
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(divider){
            bottom.linkTo(cover.bottom, margin = (-8).dp)
        }


    }

    ConstraintLayout(
        constraintSet,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 16.dp)
    ) {
        AsyncImage(
            model = videoEntity.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .layoutId("cover")
                .aspectRatio(16/9f)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = videoEntity.title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("title")
        )


        Text(
            text = videoEntity.type,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("type")
        )

        Text(
            text = "时长:${videoEntity.duration}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("duration")
        )

        HorizontalDivider(
            modifier = Modifier
                .layoutId("divider")
                .padding(top = 8.dp)
        )

    }
}