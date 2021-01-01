package com.faranjit.clean.features.spotify.playlists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.view.ActionMode
import androidx.core.app.ActivityOptionsCompat
import com.faranjit.clean.AppNavigator
import com.faranjit.clean.R
import com.faranjit.clean.core.ui.BaseActivity
import com.faranjit.clean.core.ui.RecyclerScrollListener
import com.faranjit.clean.core.ui.observeLiveData
import com.faranjit.clean.core.ui.visible
import com.faranjit.clean.databinding.ActivityPlaylistsBinding
import com.faranjit.clean.domain.spotify.model.SpotifyPlaylistItem
import kotlinx.android.synthetic.main.activity_playlists.*
import kotlinx.android.synthetic.main.list_playlist_item.view.*
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 30.03.2020.
 */
class PlayListsActivity : BaseActivity<PlayListsViewModel, ActivityPlaylistsBinding>() {

    companion object {
        fun newIntent(context: Context) = Intent(context, PlayListsActivity::class.java)
    }

    @Inject
    lateinit var viewModel: PlayListsViewModel

    private val spotifyPlaylistAdapter =
        SpotifyPlaylistAdapter(emptyList(), ::onClickPlaylistItem)

    private var actionMode: ActionMode? = null

    private lateinit var scrollListener: RecyclerScrollListener

    override fun provideLayoutId(): Int = R.layout.activity_playlists

    override fun provideViewModel(): PlayListsViewModel = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Spotify Playlists"

        viewDataBinding.viewModel = viewModel
        viewDataBinding.recyclerSpotifyPlaylist.adapter = spotifyPlaylistAdapter

        initScrollListener()
    }

    override fun initObservers() {
        viewModel.run {
            observeLiveData(hasNext) {
                if (!it) {
                    scrollListener.setOnLoadMoreListener { return@setOnLoadMoreListener }
                }
            }

            observeLiveData(unauthorizedLiveData) {
                if (it.getContentIfNotHandled() == true) {
                    viewModel.clearSpotifyAccessToken()
                    AppNavigator.openAuthorizationScreen(this@PlayListsActivity)
                }
            }

            observeLiveData(spotifyPlaylists) {
                scrollListener.setLoaded()
                it.getContentIfNotHandled()?.let { playlists ->
                    submitList(playlists)
                }
            }
        }
    }

    override fun showLoading(visible: Boolean) {
        spinner.visible(visible)
    }

    private fun initScrollListener() {
        val layoutManager = viewDataBinding.recyclerSpotifyPlaylist.layoutManager
        layoutManager?.let {
            scrollListener = RecyclerScrollListener(it).apply {
                setOnLoadMoreListener {
                    viewModel.fetchNextPlaylists()
                }
            }

            viewDataBinding.recyclerSpotifyPlaylist.addOnScrollListener(scrollListener)
        }
    }

    private fun onClickPlaylistItem(
        item: SpotifyPlaylistItem,
        viewHolder: SpotifyPlaylistViewHolder
    ) {
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            viewHolder.itemView.imgPlaylist,
            viewHolder.itemView.imgPlaylist.transitionName
        ).toBundle()

        AppNavigator.openSpotifyPlaylistDetail(
            this,
            item.id,
            item.name,
            item.images.firstOrNull()?.url,
            activityOptions
        )
    }

    private fun submitList(items: List<SpotifyPlaylistItem>) {
        spotifyPlaylistAdapter.addItems(items)
    }
}