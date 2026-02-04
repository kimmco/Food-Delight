package com.cokimutai.hoteliapp.activity.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.cokimutai.hoteliapp.domain.BannerModel
import com.cokimutai.hoteliapp.R
import kotlinx.coroutines.delay


/*
@Composable
fun Banner(banners: SnapshotStateList<BannerModel>, showBannerLoading: Boolean) {
    if (showBannerLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp),
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator()
        }
    } else {
        Banners(banners)
    }
}

@Composable
fun Banners(banners: List<BannerModel>) {
    AutoSlidingCarousel(banners = banners)
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    //pagerState: PagerState = remember { PagerState() },
    banners: List<BannerModel>
) {
    val pagerState = rememberPagerState(pageCount = { banners.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(pagerState.currentPage) {
            delay(3000) // Wait for 3 seconds
            // Animate to the next page, wrapping around to the first page if at the end
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(banners[page].image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDot = banners.size,
            selectedIndex = pagerState.currentPage,
            dotsize = 8.dp
        )

    }

}

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDot: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.orange),
    unselectedColor: Color = colorResource(R.color.grey),
    dotsize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    )
    {
        items(totalDot) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unselectedColor,
                size = dotsize
            )
            if (index != totalDot - 1)
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))

        }
    }
}

@Composable
fun IndicatorDot(modifier: Modifier = Modifier, size: Dp, color: Color) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}
*/

@Composable
fun Banner(banners: List<BannerModel>, showBannerLoading: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Set a consistent height for the container
        contentAlignment = Alignment.Center
    ) {
        if (showBannerLoading) {
            CircularProgressIndicator()
        } else if (banners.isNotEmpty()) {
            // *** THE FIX IS HERE ***
            // Only show the Banners composable if the list is not empty.
            Banners(banners)
        }
        // If it's not loading and the list is empty, this Box will simply be empty.
    }
}

@Composable
fun Banners(banners: List<BannerModel>) {
    AutoSlidingCarousel(banners = banners)
}


/*
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    banners: List<BannerModel>
) {
    // This check is good practice but the primary fix is in the Banner composable
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { banners.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(pagerState.currentPage) {
            delay(3000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
        }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(banners[page].image)
                    .build(),
                contentDescription = "Banner Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)
                    .fillMaxWidth()
            )

         /*   AsyncImage(
                model = banners[0].image,
                contentDescription = null,
                modifier = Modifier
                    .height(160.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            ) */
        }
        Spacer(modifier = Modifier.height(8.dp))
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp
        )
    }
} */

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    banners: List<BannerModel>
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { banners.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (!isDragged) {
        LaunchedEffect(pagerState.currentPage) {
            delay(3000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Add explicit height here
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Use weight instead of fixed height
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].image)
                    .crossfade(true) // Add crossfade animation
                    .build(),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop, // Changed from FillBounds to Crop
                modifier = Modifier
                    .fillMaxSize() // Fill the pager item
                    .padding(16.dp)
                    .clip(RoundedCornerShape(10.dp)),
                // Add error/loading placeholders for debugging
                error = painterResource(R.drawable.flame), // Add your error drawable
                placeholder = painterResource(R.drawable.cart) // Add placeholder
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp
        )
    }
}

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.orange),
    unselectedColor: Color = colorResource(R.color.grey),
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unselectedColor,
                size = dotSize
            )
            if (index != totalDots - 1)
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
        }
    }
}

@Composable
fun IndicatorDot(modifier: Modifier = Modifier, size: Dp, color: Color) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}