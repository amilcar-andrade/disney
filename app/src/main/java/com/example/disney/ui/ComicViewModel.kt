package com.example.disney.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.disney.api.Thumbnail
import com.example.disney.repository.MarvelComicRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class ComicViewModel(repository: MarvelComicRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _state: MutableLiveData<ComicViewState> = MutableLiveData()
    internal val state: LiveData<ComicViewState>
        get() = _state

    init {
        compositeDisposable.add(
            fetchComics(repository).subscribe({
                _state.postValue(it)
            }, {
                println(it.message) // NOT expected error
            })
        )
    }

    private fun fetchComics(repository: MarvelComicRepository) =
        Observable.fromSingle(
            repository.comics().map({
                items -> if (items.isEmpty()) {
                    return@map ComicViewState.Empty
                }
                return@map ComicViewState.Loaded(
                    items.map { i -> ComicListViewItem(
                        i.title ?: "",
                        i.description ?: "",
                        getImageUrl(i.thumbnail)
                        )
                    }
                )
            }).onErrorReturnItem(ComicViewState.Error)
        ).startWithItem(ComicViewState.Loading)

    private fun getImageUrl(thumbnail: Thumbnail?): String {
        if (thumbnail == null) {
            return ""
        }

        val path = thumbnail.path
        val extension = thumbnail.extension
        if (path.isNullOrBlank() || extension.isNullOrBlank()) {
            return ""
        }
        return "$path.$extension"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {

        fun provideFactory(repository: MarvelComicRepository) : ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ComicViewModel(repository) as T
                }
            }
        }
    }
}