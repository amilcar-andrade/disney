package com.example.disney.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

internal sealed interface ComicViewState {

    data object Empty: ComicViewState

    data class Loaded(val items: List<ComicListViewItem>): ComicViewState

    data object Loading: ComicViewState

    data object Error: ComicViewState

}

@Parcelize
internal data class ComicListViewItem(
    val name: String,
    val summary: String,
    val imageUrl: String
): Parcelable