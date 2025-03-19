package com.marcofidalgo.feature.details.ui.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.marcofidalgo.designsystem.R
import com.marcofidalgo.designsystem.utils.ImageHelper
import com.marcofidalgo.feature.details.ui.screens.components.InfoContainer
import com.marcofidalgo.feature.home.viewmodel.DetailsViewModel
import com.marcofidalgo.details.R as LR

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val breed = viewModel.selectedCatBreed.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderContainer(breed?.name, onBackClick)
        CatImage(breed?.reference_image_id)
        Origin(breed?.origin)
        Temperament(breed?.temperament)
        Description(breed?.description)
    }

}

@Composable
fun Header(breedName: String?) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = breedName ?: "",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun HeaderContainer(breedName: String?, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Button(
            contentPadding = PaddingValues(0.dp),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            onClick = onBackClick
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
        }
        Header(breedName)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatImage(catBreedId: String?) {
    GlideImage(
        model = catBreedId?.let {
            val catImageUrl = ImageHelper.getCatImage(breedId = it)
            catImageUrl
        },
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .height(250.dp),
        failure = placeholder(R.drawable.image_error_fallback),
    ) { builder ->
        builder.listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean,
                ): Boolean = false

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }
            },
        )
    }
}

@Composable
fun Origin(origin: String?) {
    InfoContainer(stringResource(LR.string.label_origin), origin)
}

@Composable
fun Temperament(temperament: String?) {
    InfoContainer(stringResource(LR.string.label_temperament), temperament)
}

@Composable
fun Description(description: String?) {
    InfoContainer(stringResource(LR.string.label_description), description)
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailsScreen() {
    MaterialTheme() {
        DetailsScreen(onBackClick = {})
    }
}
