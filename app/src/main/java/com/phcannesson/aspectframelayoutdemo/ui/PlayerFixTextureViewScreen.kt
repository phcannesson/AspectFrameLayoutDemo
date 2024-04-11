package com.phcannesson.aspectframelayoutdemo.ui

import android.view.LayoutInflater
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.phcannesson.aspectframelayoutdemo.R

@OptIn(UnstableApi::class) @Composable
fun PlayerFixTextureViewScreen() {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            // Prepare the player with the source.
            val mediaItem = MediaItem.fromUri(MEDIA_URL)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release() // Don't forget to release the player when it's no longer needed.
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val view = LayoutInflater.from(ctx).inflate(R.layout.fixed_exo_player, null, false)
            (view as PlayerView).apply {
                clipToOutline = true
                player = exoPlayer
                useController = false
            }
            return@AndroidView view
        }
    )
}

private const val MEDIA_URL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"