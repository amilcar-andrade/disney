package com.example.disney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.example.disney.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class ComicDetails : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comic_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = requireArguments().getParcelable<ComicListViewItem>(ARGS_KEY)
        val image: ImageView = ViewCompat.requireViewById(view, R.id.image)
        val title: TextView = ViewCompat.requireViewById(view, R.id.title)
        Picasso.get().load(item?.imageUrl).into(image)
        title.text = item?.name
    }

    companion object {

        private const val ARGS_KEY = "ARGS_KEY"

        internal fun create(item: ComicListViewItem): ComicDetails {
            val args = Bundle(1).apply {
                this.putParcelable(ARGS_KEY, item)
            }
            return ComicDetails().apply {
                this.arguments = args
            }
        }
    }
}