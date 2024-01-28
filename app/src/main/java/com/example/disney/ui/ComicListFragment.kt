package com.example.disney.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.disney.DisneyApplication
import com.example.disney.R
import com.example.disney.repository.MarvelComicRepository
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ComicListFragment : Fragment() {

    @Inject
    lateinit var repository: MarvelComicRepository

    private val viewModel: ComicViewModel by activityViewModels<ComicViewModel> {
        ComicViewModel.provideFactory(repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as DisneyApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comic_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = ViewCompat.requireViewById<View>(view, R.id.loading)
        val empty = ViewCompat.requireViewById<View>(view, R.id.empty)
        val error = ViewCompat.requireViewById<View>(view, R.id.error)
        val rv = ViewCompat.requireViewById<RecyclerView>(view, R.id.list)
        val adapter = ComicAdapter(Picasso.get(), object : ComicAdapter.ClickListener {
            override fun onClick(item: ComicListViewItem) {
                ComicDetails.create(item).show(childFragmentManager, "TAG")
            }
        })
        rv.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, {
            when(it) {
                ComicViewState.Empty -> {
                    empty.visibility = View.VISIBLE
                    rv.visibility = View.GONE
                    error.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
                ComicViewState.Error -> {
                    error.visibility = View.VISIBLE
                    empty.visibility = View.GONE
                    rv.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
                is ComicViewState.Loaded -> {
                    rv.visibility = View.VISIBLE
                    adapter.updateItems(it.items)
                    empty.visibility = View.GONE
                    error.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
                ComicViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    empty.visibility = View.GONE
                    error.visibility = View.GONE
                    rv.visibility = View.GONE
                }
            }
        })
    }
}